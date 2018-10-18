object main extends App {
  /*
   * Sealed classes:
   * A sealed class cannot have any new subclasses added
   * expect the ones defined in the same file.
   * 
   */
  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
  
  /*
   *  Defining a pattern that does not take into consideration
   *  all possibilities.
   *  Scala compile will warn about a non-exhaustive match pattern.
   */
  def describe(e: Expr): String = e match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
  }
  /*
   * The warning can be silenced:
   */
  def describe2(e: Expr): String = (e: @unchecked) match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
  }
  
  println(describe(Var("X")))
  println(describe(Number(10.0)))
  
  
}