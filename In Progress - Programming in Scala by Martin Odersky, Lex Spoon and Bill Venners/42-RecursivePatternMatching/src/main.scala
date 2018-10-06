object main extends App{
  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String,
                  arg: Expr) extends Expr
  case class BinOp(operator: String,
                   left: Expr,
                   right: Expr) extends Expr
                   
  def simplifyAll(expr: Expr): Expr= expr match {
    case UnOp("-", UnOp("-", e)) => simplifyAll(e) // simplify inverse of an inverse
    case BinOp("+", e, Number(0)) => simplifyAll(e) // simplify add 0
    case BinOp("*", e, Number(1)) => simplifyAll(e)
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }
  
  val v = Var("x")
  val binOp1 = UnOp("-", v)
  val binOp2 = UnOp("-", binOp1)
  
  println(simplifyAll(v))
  println(simplifyAll(binOp1))
  println(simplifyAll(binOp2))
}