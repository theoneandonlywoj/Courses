import scala.io.Source

/*
 * Script to print length of each line
 * and the line in a specified file.
 * The lines are alligned.
 * Usage:
 * scala _02_lines_alligned.scala _01_print_all_lines.scala
 */

// Function calculating width of the string representing length of the line
def widthOfLength(s: String) =  s.length.toString.length

if (args.length > 0){
  // Read lines from the file and rganize them as a List
  val lines = Source.fromFile(args(0)).getLines().toList
  
  // Get the longest line.
  // It contains of the width of the line and the line itself
  // Function reduceLeft applies the logic
  // and returns the left value for further comparison.
  val longestLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b
  )
  
  // Obtain the length of the longest line.
  // Including the number of the characters in the line
  val maxWidth = widthOfLength(longestLine)
  
  // Loop through the lines
  
  for (line <- lines){
    // Allinging by adding the spaces before the number of characters in the line.
    val numSpaces = maxWidth - widthOfLength(line)
    // Padding
    val padding = " " * numSpaces
    println(padding + line.length + " | " + line)
  }
}