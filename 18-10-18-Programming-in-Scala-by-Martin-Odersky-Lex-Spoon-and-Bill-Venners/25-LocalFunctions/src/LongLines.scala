import scala.io.Source
object LongLines {
  def processFile(filename: String, width: Int) = {
    // Local function "processLine"
    def processLine(line: String) = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}