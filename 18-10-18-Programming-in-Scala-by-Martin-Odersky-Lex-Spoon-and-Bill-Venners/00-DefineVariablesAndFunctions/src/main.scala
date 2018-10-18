object main extends App {
  /*
   * 1) Scala has two kinds of variable: vars and vals.
   * A val variable can never be reassigned. 
   * It is similar to a final variable in Java.
   * A var can be reassigned during its lifetime. 
   */
  val msg = "Hello, World!"
  /* 
   * 2) Scala is capable of type inference.
   * It means that it will automatically detect type of the information.
   */
  println(msg)
  println(msg.getClass)
  
  /* 
   * 3) We can set specific type of the variable.
   */
  val msg2: java.lang.String = "Hello again, world!"
  println(msg2)
  println(msg2.getClass)
  
  /*
   * 4) Java.lang types are visible with simpified names.
   */
  val msg3: String = "Hello yet again, world!"
  println(msg3)
  println(msg3.getClass)
  
 /*
  * 5) As mentioned before, we cannot re-assign a value to a val.
  * If we need to do it, we would use var. 
  */
  var greeting = "Hello, World!"
  println(greeting)
  greeting = "Leave me alone, please. I am programming in Scala!"
  println(greeting)
  
  /*
   * 6) Defining a function.
   * - "def" starts the function definition
   * - "max" is the function name
   * - "x" and "y" are the parameters of the function.
   *   They must be defined in the parenthesis along with their types.
   * - parameters of the function are defined using colon and type
   * - The type of the result is defined outside the parenthesis and the colon.
   * - If the function is recursive, the result type must be defined.
   *   Otherwise, it can be left out.
   *   In the example below, we will try to be explicit.
   * - Then two last things are the "=" sign and function definition inside
   *   the curly brackets "{}".
   *   
   */
  def max(x: Int, y: Int): Int = {
    if (x > y) 
      x
    else 
      y
    }
  
  println(max(10, 11))
  
  /*
   * 7) One-line function can ommit the curly brackets.
   */
  def maxOneLine(x: Int, y: Int): Int = if(x > y) x else y
  
  println(maxOneLine(11, 12))
  
  /* 
   * 8) Some functions do not need to return anything.
   * In this case the return type will be Unit.
   * Scala's unit type is similar to Java's void.
   */
  def greet(): Unit = println("Hello!")
  
  println(greet())
  println(greet().getClass())
}

