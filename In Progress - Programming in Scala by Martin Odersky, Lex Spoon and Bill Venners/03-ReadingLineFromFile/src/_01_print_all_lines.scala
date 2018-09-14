import scala.io.Source

/*
 * Script to print length of each line
 * and the line in a specified file.
 * Usage:
 * scala _01_print_all_lines.scala _01_print_all_lines.scala
 */
if (args.length > 0){
  for(line <- Source.fromFile(args(0)).getLines()){
    println(line.length + ": " + line) 
  }
}
else{
  Console.err.println("Please enter filename, please.")
}