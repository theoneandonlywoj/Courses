import scala.collection.mutable.ArrayBuffer
object main extends App {
  /*
   * Array Buffer
   */
  val buf = new ArrayBuffer[Int]
  buf += 12
  buf += 15
  println(buf)
  println(buf.length)
  println(buf(0))
  println(buf.toArray)
}