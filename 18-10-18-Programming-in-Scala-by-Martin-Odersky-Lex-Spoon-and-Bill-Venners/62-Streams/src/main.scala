import scala.collection.immutable.Stream
object main extends App {
  /*
   * Streams:
   * Stream is very similar to a list.
   * The main difference is that elements of a stream
   * is lazily computed, which means that only requested elements
   * will be computed.
   * Streams are constructed with operation #::
   */
  // At this point the stream is not computed!
  val stream1 = 1 #:: 2 #:: 3 #:: Stream.empty
  // Example in a method:
  def fibFrom(a: Int, b: Int): Stream[Int] = 
    a #:: fibFrom(b, a + b)
  val fibs = fibFrom(1, 1)
  println(fibs)
  println(fibs.take(7))
  println(fibs.take(7).length)
  println(fibs.take(7).toList)
}