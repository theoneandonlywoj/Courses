object main extends App{
  // Return a row as sequence
  def makeRowSeq(row: Int): IndexedSeq[String] ={
    for (col <- 1 to 10) yield{
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }
  }
  // Return a row as a string
  def makeRow(row: Int): String = makeRowSeq(row).mkString
  // Return table as a string with one row per line
  def multiplicationTable(): String = {
    val tableSeq = 
      for(row <- 1 to 10) yield makeRow(row)
    tableSeq.mkString("\n")
  }
  
  println(multiplicationTable())
}