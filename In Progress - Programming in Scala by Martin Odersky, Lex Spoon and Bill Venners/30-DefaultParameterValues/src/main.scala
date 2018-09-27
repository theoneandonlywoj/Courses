object main extends App{
  // Scala allows defining default values for the function parameters.
  def add (x: Int, y: Int = 0): Int = {
    x + y
  }
  
  val a = 1
  val b = 2
  println(a)
  println(add(a))
  println(add(a, b))
}