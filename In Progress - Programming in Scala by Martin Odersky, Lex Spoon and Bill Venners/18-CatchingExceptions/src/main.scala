import java.io.{FileReader, 
                FileNotFoundException,
                IOException}
object main extends App{
  try{
    val f = new FileReader("nonExistingFile.txt")
  }catch{
    case ex: FileNotFoundException => println("File Not Found!")
    case ex: IOException => println("IO Exception!")
  }
  
}