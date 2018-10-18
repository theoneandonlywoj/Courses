import scala.collection.mutable.ListBuffer
object main extends App {
  val buf = new ListBuffer[Int]
  // Appending a value
  buf += 2
  println(buf)
  buf += 3
  println(buf)
  // Prepending a value
  4 +=: buf
  // ListBuffer to List
  println(buf)
  println(buf.toList)  
}