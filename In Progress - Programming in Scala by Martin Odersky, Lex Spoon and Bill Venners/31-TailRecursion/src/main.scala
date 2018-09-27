object main extends App{
  /*
   * Tail Recursion:
   * Functions, which the last action is calling themselves
   * are called tail recursive.
   * Tail recursive solution (over the while loop version)
   * does not suffer from an overhead.
   * 
   * Tail-recursive will not build a new stack each frame,
   * all call will be executed in a single frame.
   * 
   * Function below is not a tail recursive function because
   * it performs and addition after calling itself:
   */
  
  def notTailRecursive(x: Int): Int = {
    println(x)
    if (x == 0) {
      println("Finished notTailRecursive!")
      x
    }
    else notTailRecursive(x-1) + 1
  }
  
  println(notTailRecursive(2))
  
  // Example of a tail-recursive function
  def tailRecursive(x: Int): Int ={
    println(x)
    if (x == 0) {
      println("Finished notTailRecursive!")
      x
    }
    else tailRecursive(x-1)
  }
  
  println(tailRecursive(2))
  
  /*
   * Limits of tail recursion optimization:
   * Scala (due to limitations of JVM) optimises only directive 
   * recursive calls to itself.
   * Following code will not be optimised:
   */
  def isEven(x: Int): Boolean = {
    if (x == 0) true else isOdd(x - 1)
  }
  def isOdd(x: Int): Boolean = {
    if (x == 0){
      println("x == 0")
      false 
    } else {
      isEven(x - 1)
    }
  }
  println(isEven(5))
}