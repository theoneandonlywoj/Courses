object main extends App {
  /*
   * Implicit classes:
   * Implicit classes cannote be case class
   * and its constructor must have exactly one parameter.
   * Furthermore, an implicit class must be located
   * within some other object, class or trait.
   */
  class Rectangle(width: Int, height: Int)
  implicit class RectangleMaker(width: Int){
    def x(height: Int) = new Rectangle(width, height)
  }
  /*
   * The compiler generates code:
   * implicit def RectangleMaker(width: Int) =
   *   new RectangleMaker(width)
   */
  val myRectangle = 3 x 4
  println(myRectangle)
  
  /*
   * Type Int have no method "x".
   * Thus, the compiler will look for an implicit conversion.
   */
}