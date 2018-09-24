import java.io.FileReader
object main extends App{
  /*
   * The finally clause is used if you want to cause some code 
   * to be executed no matter how the expression terminates.
   */
  val file = new FileReader("./src/input.txt")
  try{
    // Do something here!
    println("Doing something with the file!")
  } finally {
    println("Finished!")
    file.close()
    println("File closed!")
  }
}