object main {
  def main(args: Array[String]){
    /*
     * A shadow variable is a variable inside a function or a method that is of the same name
     * as the outer variable, but it is not visible to the outer part of the program
     * Examples below:
     */
    val a = 1
    /*
     *  1) Uncommenting line below will cause an error due to sharing the same scope
     *  by two values (val) of the same name.
     *  Thus, it is a re-assignment, which is not possible with vals.
     */
    // val a = 2
    println(a)
    
    /*
     * 2) Example below presents a shadow variable.
     */
    val b = 1
    object objectA{
      val b = 2
      println(b)
    }
    objectA
    println(b)
    
  }
}