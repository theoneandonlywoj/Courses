import scala.collection.immutable.Queue
object main extends App {
  /*
   * Queues:
   * Queues encapsulate variables in first-in-first-out manner.
   */
  val emptyQueue = Queue[Int]()
  println(emptyQueue)
  // Adding new variable at the end. New queue!
  val has1 = emptyQueue.enqueue(1)
  println(has1)
  val has12345 = has1.enqueue(List(2, 3, 4, 5))
  // Removing head from the queue
  val (element, has2345) = has12345.dequeue
  println(element)
  println(has2345)
}