import scala.collection.mutable.ListBuffer
object main extends App {
  val xs = List(1, 2, 3)
  // List buffer
  val buf = new ListBuffer[Int]
  for (x <- xs) buf += x + 1
  println(buf.toList)
}