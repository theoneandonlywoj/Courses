object main extends App {
  /*
   * Every class inherits from a superclass called Any.
   * Methods defined in Any are universal 
   * and they might be invoked on any object.
   * List of classes implemented in Any:
   * 1) == (final)
   * 2) != (final
   * 3) equals
   * 4) ## 
   * 5) hashCode
   * 6) toString
   * 
   * Final methods (== and !=) cannot be overridden in subclasses.
   * Methods "equals" and "==' are the same.
   * The only difference is when the code calls for Java boxed numeric classes.
   * Example of that can be Integer and Long
   * where 1 will not be equal to 1L.
   */
  val x = "abcd".substring(2)
  println(x)
  val y = "cd"
  println(y)
  
  println(x == y)
  
  val xString = new String("abc")
  val yString = new String("abc")
  
  println(x == y)
  println(x eq y)
  println(x ne y)
  
  /*
   * Scala has two "corner cases" object types: Null and Nothing.
   * 
   * Class Null is the type of the null reference.
   * It is a subclass of every reference class (AnyRef).
   * 
   * Class Nothing is used to indicate abnormal termination.
   * The return type Nothing tells the user that the method
   * will not return normally (f.e. throw an exception).
   * 
   * It is a subtype of all other types.
   * Example:
   */
  
  def divide(x: Int, y: Int): Int = {
    if (y != 0) x / y
    /*
     *  Line below will return Nothing,
     *  but it is a subtype of all other types,
     *  so it is a subtype of Int as well.
     *  Return type Int is satisfied.
     */
    else sys.error("Can't divide by zero!")
  }
}