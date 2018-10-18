import scala.util.control.Breaks._
import java.io._
object main extends App{
  
  val input = new BufferedReader(new InputStreamReader(System.in))
  
  breakable{
    while(true){
      println("? ")
      if (input.readLine() == ""){
        println("Finished!")
        break
      }
    }
  }
}