object main extends App {
  /*
   * Instead of implementing > and <, 
   * we can use the Ordered trait.
   * It requires a type parameters.
   * Thus, mixing it in is done using:
   * "Ordered[ClassName]"
   * 
   * The Ordered trait does not provide the equals methods.
   * They need to be implemented seperately.
   */
  class Rational (n: Int, d: Int) extends Ordered[Rational] { 
    val numerator: Int = n
    val denominator: Int = d
    def compare(that: Rational): Int = {
      (this.numerator * that.denominator) - (that.numerator * this.denominator)
    }
  }
  
  val half = new Rational(1, 2)
  val third = new Rational(1, 3)
  println(half > third)
}