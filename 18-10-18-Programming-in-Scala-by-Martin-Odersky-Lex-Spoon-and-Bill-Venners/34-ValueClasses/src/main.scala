object main extends App {
  /*
   * Value Classes:
   * To be a value class means the class:
   * 1) must have exactly one parameter
   * 2) must contain only defs
   * 3) no other class can extends th values class
   * 4) cannot redefine equals or hashCode
   */
  class Dollars(val amount: Int) extends AnyVal { 
    override def toString() = "$" + amount 
  }
  class SwissFrancs(val amount: Int) extends AnyVal {
    override def toString() = amount + " CHF"
  }
  val dollars = new Dollars(1000)
  /*
   *  Value dollars is of type Dollars,
   *  but compiled to the Java byte code of type Int.
   */
  println(dollars)
  println(dollars.amount)
  
  val francs = new SwissFrancs(1000)
  println(francs)
  println(francs.amount)
}