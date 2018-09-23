object main extends App{
  val filesHere = (new java.io.File("./src/")).listFiles()
  def scalaFiles = 
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file
  for (scalaFile <- scalaFiles) {
    println(scalaFile)
  }
}