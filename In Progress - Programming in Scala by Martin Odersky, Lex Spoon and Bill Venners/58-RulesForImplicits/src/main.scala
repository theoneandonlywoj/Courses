object main extends App {
  /*
   * Implicit conversions:
   * Implicits can be inserted into a program
   * to fix any of its type errors.
   * For example:
   * If an operation (f.e. x + y) does not type check
   * the compiler might changes it to convert(x) + y,
   * where convert is some available implicit conversion.
   */
  /*
   * 1) Only definitions marked "implicit" are available.
   * Example: implicit method to convert Int to Str
   */
  implicit def intToString(x: Int): String = x.toString
  def printString(x: String): Unit = { println(x) }
  printString(1)
  /*
   * 2) An inserted implicit conversion must be in scope
   * as a single identifier or to be associated
   * with the source or target type of the conversion.
   * - Single identifier: 
   * The compiler will not insert a conversion 
   * of the form someVariable.convert(x).
   * - Exception:
   * One exception to the rule is the companion object.
   * An implicit conversion therefore could be packaged
   * in the class or the companion object.
   */
  
  object Euro
  class Euro
  object Dollar {
    implicit def dollarToEuro(x: Dollar): Euro = 
      new Euro
  }
  class Dollar
  /*
   * 3) Only one implicit is inserted.
   * The compiler will do not compound operation as below:
   * convert1(convert2(x)) + y
   * However, it is possible to ceate such operation
   * by having implicit take implicit parameters.
   * 
   * 4) Explicits-first.
   * The compiler WILL NOT replace code of which type checks.
   */
}