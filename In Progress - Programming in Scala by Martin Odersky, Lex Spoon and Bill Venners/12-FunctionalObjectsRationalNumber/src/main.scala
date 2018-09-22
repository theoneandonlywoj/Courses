object main extends App{
  /*
   * A rational number is a number that can be expressed as a ratio n / d.
   * Both n and d are integers, d cannot be 0:
   * n - nominator
   * d - denominator
   * 
   * Rational number have advantage of over the floating number
   * by representing the number without rounding or approximation.
   */
  class Rational(n: Int, d: Int){
    // Requiring that the denominator is not zero.
    require(d != 0)
    // Greatest common divisor (private value).
    private val g = greatestCommonDivisor(n.abs, d.abs)
    /*
     *  Making n and d into fields.
     *  Dividing by the greatest common divisor.
     */
    val numerator: Int = n / g
    val denominator: Int = d / g
    /*
     * Auxiliary constructor
     * In Scala, an axiliary constructors is defined with "this".
     */
    def this(n: Int) = this(n, 1)
    // Methods
    override def toString = {n + "/" + d}
    // Addition
    def add (that:Rational): Rational = {
      new Rational(
          numerator * that.denominator + that.numerator * denominator,
          denominator * that.denominator
          )
    }
    def + (that:Rational): Rational = {
      new Rational(
          numerator * that.denominator + that.numerator * denominator,
          denominator * that.denominator
          )
    }
    def + (i: Int): Rational = {
      new Rational(
          numerator  + i * denominator,
          denominator
          )
    }
    // Substraction
    def - (that:Rational): Rational = {
      new Rational(
          numerator * that.denominator - that.numerator * denominator,
          denominator * that.denominator
          )
    }
    def - (i: Int): Rational = {
      new Rational(
          numerator  - i * denominator,
          denominator
          )
    }
    // Multiplication
    def * (that:Rational): Rational = {
      new Rational(
          this.numerator * that.numerator,
          this.denominator * that.denominator
          )
    }
    def * (i:Int): Rational = {
      new Rational(
          this.numerator * i,
          this.denominator
          )
    }
    // Division
    def / (that:Rational): Rational = {
      new Rational(
          this.numerator * that.denominator,
          this.denominator * that.numerator
          )
    }
    def / (i:Int): Rational = {
      new Rational(
          this.numerator,
          this.denominator * i
          )
    }
    // Comparing two rational numbers
    def lessThan(that: Rational): Boolean = {
      this.numerator * that.denominator < that.numerator * this.denominator
    }
    def max(that:Rational): Rational = {
      if(this.lessThan(that)) that else this
    }
    def greatestCommonDivisor(a: Int, b: Int): Int = {
      if (b == 0) a else greatestCommonDivisor(b, a % b)
    }
  }
  
  // Int to Rational
  implicit def intToRational(x: Int): Rational = new Rational(x)
  // Rational number examples
  val oneHalf = new Rational(1, 2)
  val twoThird = new Rational(2, 3)
  println(oneHalf.toString)
  println(oneHalf.numerator)
  println(oneHalf.denominator)
  
  println(oneHalf.lessThan(twoThird))
  println(oneHalf lessThan twoThird)
  println(oneHalf max twoThird)
  
  println(oneHalf + twoThird)
  println(oneHalf - twoThird)
  println(oneHalf * twoThird)
  println(oneHalf / twoThird)
  
  println(oneHalf + 1)
  println(oneHalf - 1)
  println(oneHalf * 2)
  println(oneHalf / 3)
  
  val result = (oneHalf / 7) + (1 - twoThird)
  println(result)
}