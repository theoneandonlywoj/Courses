import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try
object main extends App {
  /*
   * Futures:
   */
  // Bad approach:
  val fut: Future[Int] = Future { Thread.sleep(1000); 21 + 21 }
  while (!fut.isCompleted)
    println("Not finished yet!")
    Thread.sleep(500)
  println("Done!")
  // It is better to wait:
  val fut2: Future[Int] = Future { Thread.sleep(4000); 7 }
  println("Waiting...")
  val x: Int = Await.result(fut2, 6.seconds)
  println("x: " + x.toString)
  /*
   * Transforming Future with map
   */
  val fut3: Future[Int] = Future { Thread.sleep(5000); 6 + 1 - 2 }
  val xWithMap: Future[Int] = fut3.map(x => x + 1)
  println("Waiting for x with map...")
  println(Await.result(xWithMap, 6.seconds))
  
  /*
   * Filtering: filter and collect
   * Method "collect" allows to validate the future value
   * and transform it in one operation.
   * It can return NoSuchElementException!
   */
  
  val fut4 = Future { 1 }
  val valid = fut4.filter(res => res > 0)
  val validResult: Int = Await.result(valid, 1.seconds)
  val validTry: Option[Try[Int]] = valid.value
  val validCollect: Future[Int] = fut4.collect{
    case res if res > 0 => res + 6
  }
  println(validResult)
  println(validTry)
  println(validCollect)
  println(validCollect.value)
  
  /*
   * Dealing with failure:
   * 1) failed
   * 2) fallbackTo
   * 3) recover
   * 4) recoverWith
   */
  val myFailure: Future[Int] = Future { 42 / 0 }
  val expectedFailure: Future[Throwable] = myFailure.failed
  println(Await.result(expectedFailure, 1.seconds))
  val mySuccess: Future[Int] = Future { 42 / 1 }
  val unexpectedSuccess: Future[Throwable] = mySuccess.failed
  /*
   * Line below will throw NoSuchElementException
   * if unexpectedSuccess is successful.
   * println(Await.result(unexpectedSuccess, 1.seconds))
   */
  val fallback: Future[Int] = myFailure.fallbackTo(mySuccess)
  println(Await.result(fallback, 1.seconds))
  // Failed fallback.
  val failedFallback: Future[Int] = myFailure.fallbackTo(
      Future {
        val res = 42
        require(res < 0)
        res
        }
      )
  println(failedFallback)
  // Recovery.
  val myRecovery: Future[Int] = failedFallback.recover{
    case ex: ArithmeticException => 1
  }
  println(myRecovery)
  // Unrecovered.
  val myUnrecovery: Future[Int] = failedFallback.recover{
    case ex: IllegalArgumentException => 2
  }
  println(myUnrecovery)
  /*
   * Re-recovery with recoverWith:
   * Method recoverWith allows to recover to a Future value.
   */
  val myFailure2: Future[Int] = Future { 7 / 0 }
  val failedFallback2: Future[Int] = myFailure2.fallbackTo(
      Future {
        val res = 7
        require(res < 0)
        res
        }
      )
  val myRerecovery: Future[Int] = failedFallback2.recoverWith{
    case ex: ArithmeticException => Future { 1 + 1 }
  }
  println(Await.result(myRerecovery, 2.seconds))
}