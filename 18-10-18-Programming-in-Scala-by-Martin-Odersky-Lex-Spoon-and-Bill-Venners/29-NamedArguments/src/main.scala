object main extends App{
  // Defining the function
  def speed(distance: Float, time: Float): Float = {
    distance / time
  }
  /*
   *  Distance and time are matched
   *  with the order it is specified when the method is defined.
   */
  
  println(speed(100, 10))
  println(speed(distance = 100, time = 10))
  
  /* 
   * Named and positional arguments can be mixed.
   */
  def multiplyABC(a: Int, b: Int, c: Int): Int = {
    println("a: " + a.toString)
    println("b: " + b.toString)
    println("c: " + c.toString)
    a * b * c
  }
  multiplyABC(1, b = 2, c = 3)
  multiplyABC(1, b = 2, 3)
}