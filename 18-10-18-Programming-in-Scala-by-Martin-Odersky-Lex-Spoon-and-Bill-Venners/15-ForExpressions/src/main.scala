object main extends App{
  // Iteration through collections
  val filesHere = (new java.io.File(".")).listFiles()
  for (file <- filesHere){
    println(file)
  }
  
  /*
   * Iteration with a range.
   * Range can be created like "1 to 5".
   * That will create a range 1, 2, 3, 4, including 5.
   */
  println("1 to 5")
  for (i <- 1 to 5){
    println("Iteration " + i)
  }
  
  /*
   * Iteration with a range, excluding the last value
   * That can be done with "1 until 5"
   */
  println("1 until 5")
  for (i <- 1 until 5){
    println("Iteration " + i)
  }
  
  /*
   * Filtering
   */
  val filesHere2 = (new java.io.File("./src/")).listFiles
  for (file <- filesHere2
      if file.getName.endsWith(".scala")){
    println(file)
  }
  
  /*
   * Nested iterations
   */
  
  println("Nested iterations: ")
  def fileLines(file: java.io.File) = {
    scala.io.Source.fromFile(file).getLines().toArray
  }
  
  def grep(pattern: String) = 
    for {
        file <- filesHere2
        if file.getName.endsWith(".scala")
        line <- fileLines(file)
        if line.trim.matches(pattern)
    } println(file + ": " + line.trim)
  
    grep(".*Nested.*")
}