/*
 * To run the script, LongLines.scala needs to be compiled.
 * Run as follows:
 * 1) scalac LongLines.scala
 * 2) scala FindLongLines.scala 15 LongLines.scala
 */

object FindLongLines {
  def main(args: Array[String]): Unit = {
    val width = args(0).toInt
    val filename = args(1).toString
    LongLines.processFile(filename, width)
  }
}