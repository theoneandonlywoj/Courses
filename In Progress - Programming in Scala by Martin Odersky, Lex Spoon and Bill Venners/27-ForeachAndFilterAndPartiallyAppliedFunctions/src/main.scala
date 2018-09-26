import scala.math.pow
object main extends App{
  /*
   * Foreach.
   */
  val someNumber = List(-11, -10, -5, 0, 5, 10, 11)
  someNumber.foreach((x: Int) => println(pow(x, 2)))
  /*
   *  Partially applied function
   *  1st version:
   */
  someNumber.foreach(println(_))
  // 2nd version
  someNumber.foreach(println _)
  // Filter
  val onlyPositiveNumbers = someNumber.filter((x: Int) => x > 0)
  println(onlyPositiveNumbers)
  
  // Filter with placeholder syntax
  val onlyPositiveNumberPlaceholder = someNumber.filter(_ > 0)
}