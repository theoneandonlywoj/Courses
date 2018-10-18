import scala.collection.mutable.Queue
object main extends App {
  /*
   * Mutable Queue
   */
  val queue = new Queue[String]
  // Appending a single element
  queue += "a"
  // Appending multiple elements
  queue ++= List("b", "c")
  // Appending a single element again
  queue += "d"
  // Operations on the queue
  println(queue)
  // First-in going out and leaving only the rest.WW
  println(queue.dequeue)
  println(queue)
}