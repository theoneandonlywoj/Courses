/*
 * Scala Standalone Application
 * To create Scala's application
 * we must create a standalone singleton
 * with a main method that takes one parameter Array[String].
 * Scala does not require that the files has the same object as the name of the file.
 * However, it is recommended.
 * Run with:
 * scala main.scala Here are my arguments
 */

object MainObjects {
  def main(args: Array[String]): Unit= {
    for(arg <- args){
      println(arg)
    }
  }
}