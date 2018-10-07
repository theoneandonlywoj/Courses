object main extends App {
  /*
   * map:
   * Operation "map" takes operand List[T]
   * and a function of type T => U. 
   * It returns the list that resuts from applying the function f
   * to each list List[T] element.
   */
  val x1 = List(1, 2, 3)
  val x2 = x1.map(_ + 1)
  println(x1)
  println(x2)
  
  val x3 = List("the", "naughty", "kitty")
  val x4 = x3.map(_.length)
  val x5 = x3.map(_.toList.reverse.mkString)
  println(x3)
  println(x4)
  println(x5)
  /*
   * flatMap:
   * Operation "flatmap" is similar to "map".
   * flatMap operator take a function returning a list of elements
   * and concatenates all of the lists into a single list.
   */
  def f(x:String): List[(Int, String)] = {
    List((x.length, x))
  }
  val x6 = x3.flatMap(x => f(x))
  println(x6)
  println(x6(0)._1)
  // Example string -> list of char -> list of all characters
  def stringToListChar(s: String): List[Char] = {
    s.toList
  }
  val x7 = x3.flatMap(x => stringToListChar(x))
  println(x7)
  
  /*
   * forEach:
   * Operation "forEach" takes a procedure
   * (function that results with Unit).
   * The result of the operation "forEach" is unit
   */
  val x8 = x3.foreach(println(_))
  
  // Sum example:
  var sum: Int = 0
  def addToSum(x: Int): Unit = {
    sum += x
  }
  val x9 = List(1, 2, 3, 4, 5)
  x9.foreach(addToSum(_))
  println(sum)
  
  /*
   * filter
   */
  val x10 = x9.filter(_ > 3)
  println(x10)
  
  /*
   * partition:
   * Operation "partition" is similar to operation "filter",
   * however, it returns a pair of List,
   * first, that contains all elements that fulfill the requirement,
   * second, those that do not.
   */
  val x11 = x9.partition(_ > 1.5)
  println(x11)
  
  /*
   * find:
   * Operation "find" returns only the first element
   * that satisfy the criteria.
   * It returns Some(x), where x is the first value
   * that satisfies the criteria.
   */
  val x12 = x9.find(_ > 1.5)
  println(x12)
  
  /*
   * takeWhile:
   * Operation "takeWhile" takes returns a List of values,
   * consist of values from the first value up to value,
   * which does not satisfies.
   */
  val y = List(1, 2, 3, 4, -5, 6, 7, 8)
  val y1 = y.takeWhile(_ > 0)
  println(y)
  println(y1)
  
  /*
   * dropWhile:
   * Operation "dropWhile" drops all values from a given List,
   * up to a value that satisfies given requirement
   * and all values afterward.
   */
  println(y.dropWhile(_ > 0))
  
  /*
   * span:
   * Operation "span" combines operations 
   * "takeWhile" and "dropWhile".
   * It returns a tuple of Lists.
   */
  val y2 = y.span(_ > 0)
  println(y2)
  println(y2._1 == y.takeWhile(_ > 0))
  println(y2._2 == y.dropWhile(_ > 0))
  
  /*
   * forall:
   * Operation "forall" return Boolean,
   * if all elements satisfy given requirement.
   */
  val y3 = List(1, 2, 3, 4, 5)
  println(y3)
  println(y3.forall(_ > 0))
  println(y3.forall(_ > 3))
  
  /*
   * exists:
   * Operation "exists" returns Boolean,
   * if there is at least one element,
   * which satisfies given requirement.
   */
  println(y3.exists(_ > 0))
  println(y3.exists(_ > 10))
  
  /*
   * "/:":
   * "/:" is pronounced as "fold left".
   * Its functionality can be presented as follows,
   * where function f accepts two arguments:
   * (z :/ List(a, b, c)(f) is the same as
   * f(f(f(z, a), b), c)
   */
  def addTwoInts(x: Int, y: Int): Int = x + y
  
  val list1  = List(1, 2, 3, 4)
  val sumList1 = (0 /: list1)(addTwoInts(_, _))
  println(sumList1)
  
    /*
   * ":\":
   * ":\" is pronounced as "fold right".
   * Its functionality can be presented as follows,
   * where function f accepts two arguments:
   * (List(a, b, c) :\ z)(f) is the same as
   * f(a, f(b, f(c, z)))
   */
  val list2 = List(1.0, 0.5, 4.0)
  def divideDoubleByDouble(x: Double, y: Double): Double = 
    x / y
  val dividedResult: Double = (list2 :\ 2.0)(divideDoubleByDouble(_, _))
  val dividedResult2: Double = 
    divideDoubleByDouble(list2(0),
        divideDoubleByDouble(list2(1),
            divideDoubleByDouble(list2(2), 2.0)))
  println(dividedResult)
  println(dividedResult2)
}