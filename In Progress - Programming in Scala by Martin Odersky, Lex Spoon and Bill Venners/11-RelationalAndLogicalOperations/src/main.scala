object main extends App {
  // Greater than
  println(1 > 2)
  
  // Greater than or equal
  println(3.5f >= 3.6f)
  
  // Less than
  println(1 < 2)
  
  // Less than or equal
  println(1.0 <= 1.0)
  println(1.0 <= 1.5)
  
  // Negation
  println(!true)
  
  /*
   * Operations AND (&&) and OR (||) are evaluated in a way that
   * if the left side determines the output,
   * the right side will not be evaluated.
   */
  println("&& and ||")
  def salt() = { println("salt"); false }
  def pepper() = { println("pepper"); true }
  
  println(pepper() && salt())
  println(salt() && pepper())
  
  /*
   * To force right side evaluation use:
   * & and | instead of && and ||
   */
  println("Forced right side evaluation:")
  println(salt() & pepper())
}