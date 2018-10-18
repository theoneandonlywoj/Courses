object main extends App{
  // Defining a while loop version of the greater common divisor method
  def greatestCommonDivisorWhileLoop(x: Long, y: Long): Long = {
    var a: Long = x
    var b: Long = y
    while (a != 0){
      val temp = a
      a = b % a
      b = temp
    }
    b
  }
  def greatestCommonDivisorRecursive(x: Long, y: Long): Long = {
    if (y == 0) x else greatestCommonDivisorRecursive(y, x % y)
  }
  println(greatestCommonDivisorWhileLoop(4, 2))
  println(greatestCommonDivisorRecursive(4, 2))
}