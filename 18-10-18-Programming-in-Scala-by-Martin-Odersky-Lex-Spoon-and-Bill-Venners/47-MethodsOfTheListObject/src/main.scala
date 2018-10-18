object main extends App {
  /*
   * .apply:
   * List.apply(1, 2, 3) results in the same as List(1, 2, 3)
   */
  val list1: List[Int] = List.apply(1, 2, 3)
  println(list1)
  /*
   * .range:
   * Creating a range of numbers.
   * It can be of form:
   * 1) List.range(start, endExcluded)
   * 2) List.range(start, endExcluded, step)
   */
  val list2: List[Int] = List.range(1, 5)
  val list3: List[Int] = List.range(1, 9, 2)
  val list4: List[Int] = List.range(9, 1, -3)
  
  println(list2)
  println(list3)
  println(list4)  
  
  /*
   * .fill:
   * List.fill can be used to create uniform Lists.
   * The syntax is as follows:
   * List.fill(nElements)(elementToBeRepeated)
   * It allows to create more advanced List of Lists as well.
   */
  val list5: List[String] = List.fill(5)("abc")
  val list6: List[List[String]] = List.fill(5, 2)("abc")
  println(list5)
  println(list6)
  /*
   * .tabulate:
   * List.tabulate creates a list of elements
   * created based on the provided function.
   * Syntax:
   * List.tabulate(nElement)(function)
   * or for List of Lists:
   * List.tabulate(x, y)(function)
   */
  val list7: List[Int] = List.tabulate(5)(x => x * x)
  val list8: List[List[Int]] = List.tabulate(5, 5)(_ * _)
  val list9: List[List[(Int, Int)]] = List.tabulate(5, 5)((x, y) => (x, x * y))
  println(list7)
  println(list8)
  println(list9)
  
  /*
   * .concat:
   */
  val list10: List[Int] = List(1, 2, 3)
  val list11: List[Int] = List(5, 4)
  val list12: List[Int] = List.concat(list10, list11)
  println(list12)
  
}