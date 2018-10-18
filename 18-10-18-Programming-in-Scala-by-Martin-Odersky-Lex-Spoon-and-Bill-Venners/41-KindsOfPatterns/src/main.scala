object main extends App {
  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String,
                  arg: Expr) extends Expr
  case class BinOp(operator: String,
                   left: Expr,
                   right: Expr) extends Expr

  /*
   * Wildcard pattern:
   * Wildcard pattern "_" matches all objects.
   * It can be used to to ignore certain objects.
   */
  def wildcardPatternExample(expr: Expr): Unit = expr match {
    case BinOp(_, _, _) => println(expr + " is a binary operation.")
    case _ => println("It's something else.")
  }
  println(wildcardPatternExample(BinOp("+", Var("X"), Number(0))))
  println(wildcardPatternExample(Var("X")))

  /*
   * Constant pattern:
   */

  def constantPatternExample(x: Any) = x match {
      case 5 => "five"
      case true => "true"
      case "hello" => "hi!"
      case Nil => "The empty list!"
      case _ => "Something else."
  }
  println(constantPatternExample(5))
  println(constantPatternExample(true))
  println(constantPatternExample("hello"))
  println(constantPatternExample(Nil))
  println(constantPatternExample(List()))
  println(constantPatternExample(List(1, 2, 3)))
  /*
   * Construcor pattern:
   */
  def constructorPatternExample(expr: Expr): String = expr match {
    case BinOp("+", Var("X"), Number(0)) => "Deep match!"
    case _ => "Something else"
  }
  println(constructorPatternExample(Var("X")))
  println(constructorPatternExample(BinOp("+", Var("X"), Number(0))))
  println(constructorPatternExample(BinOp("+", Var("X"), Number(1))))
  
  /*
   * Sequence pattern:
   * To match against a List or an Array without specifying the length,
   * we can use "_*"
   */
  
  def sequencePatternExample1(x: List[Any]): String = x match {
    case List(0, _, _) => "Found it!"
    case _ => "Something else!"
  }
  println(sequencePatternExample1(List(0, 0.5, "hello")))
  println(sequencePatternExample1(List(1, 0.5, "hello")))
  
  def sequencePatternExample2(x: List[Any]): String = x match {
    case List(0, _*) => "Found it!"
    case _ => "Something else!"
  }
  println(sequencePatternExample2(List(0, 0.5, "hello")))
  println(sequencePatternExample2(List(0, 0.5)))
  println(sequencePatternExample2(List(1, 0.5, "hello")))
  /*
   * Tuple pattern:
   */
  def tuplePatternExample(x: Any): String = x match {
    case (a, b, c) => "matched " + a + b + c
    case _ => "not matched"
  }
  
  println(tuplePatternExample(("x ", "y ", "z ")))
  println(tuplePatternExample((1, 2, 3)))
  println(tuplePatternExample((1, 2)))
  
  /*
   * Typed patterns:
   */
  def generalizeSize(x: Any): Int = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
  }
  
  println(generalizeSize("xyz"))
  println(generalizeSize(Map(1 -> "a",
                             2 -> "b")))
  println(generalizeSize(5))
  
  
  
  
}