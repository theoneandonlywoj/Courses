object main extends App{
  /*
   * Case classes:
   * 1) Simplify the interface (no need to use "new")
   * 2) All arguments passed to the constructor
   *    are automatically promoted to members.
   * 3) The compiler adds implementation of toString, hashCode and equals
   * 4) The compiler adds implementation of method copy
   *    to make a modified copy of the class.
   */
  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
  
  val v = Var("x")
  val op = BinOp("+", Number(1), v)
  println(v.name)
  println(op.left)
  println(op)
  println(op.right == Var("x"))
  
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e // Double negation
    case BinOp("+", e, Number(0)) => e // Adding zero
    case BinOp("*", e, Number(1)) => e // Multiplying by one
  }
  
  val b = UnOp("-", Number(1))
  val c = simplifyTop(UnOp("-", b))
  println(b)
  println(c)
}