import java.math.BigInteger

object main extends App{
  // Value initialization with parameters
  val x = new java.math.BigInteger("12345")
  println(x)
  
  // Array
  val greetingStrings = new Array[String](_length = 4)
  greetingStrings(0) = "Hello"
  greetingStrings(1) = ", "
  greetingStrings(2) = "World"
  greetingStrings(3) = "!"
  
  for (i <- 0 to 3){
    print(greetingStrings(i))
  }
 
  // Explicit definition of an array
  val greetingStrings2: Array[String] = new Array[String](4)
  
  // Different seting approach
  greetingStrings2.update(0, "Hello")
  greetingStrings2.update(1, ", ")
  greetingStrings2.update(2, "World2")
  greetingStrings2.update(3, "!") 
  
  // Different loop definition
  for (i <- 0.to(3)){
    print(greetingStrings2(i))
  } 
  println("")
  
  // Additional ways to initialize an array
  val numNames = Array("zero", "one", "two")
  
  /*
   * List:
   * Scala Array is a mutable sequence of objects of the same type.
   * For example: Array[String].
   * You can't change the length of the array, but you can change the elements.
   * Scala List is designed for functional programming.
   * It consists of objects of the same type, but it is immutable.
   */
  val oneTwoThree = List(1, 2, 3) 
  println(oneTwoThree(1))
  
  /*
   * List concatenation:
   * Use ":::" to concatenate two lists together.
   */
  
  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  
  val oneTwoThreeFour = oneTwo ::: threeFour
  
  println(oneTwoThreeFour)
  println(oneTwoThreeFour(3))
  
  /*
   * Cons:
   * To prepends (like append, but at the beginning of the list) use operation
   * called "cons".
   * It can be invoked by "::"
   */
  
  val twoThree = List(2, 3)
  val oneTwoThree2 = 1 :: twoThree
  
  println(oneTwoThree2)
  
  /* 
   * Important:
   * Operation can be divided into left operand and right operand.
   * Left operands operate on the object on the left side.
   * An example of a left operand is multiplication "*".
   * 2 * 3 is same as 2.*(3).
   * 
   * Right operands operate on the object on the right side.
   * An example of a right operand is cons "::"
   * 1 :: oneTwo is same as oneTwo.::(1)
   * 
   * Class List does not offer operation "append", because it scales
   * linearly with the size of the list, which is very inefficient
   * with big lists.
   * However, prepending takes a constant time.
   */
  
  /*
   * An empty List can be invoked by: Nil or List()
   */
  
  val emptyList = Nil
  val notSoEmptyList = 1 :: 2 :: 3 :: Nil
  
  /*
   * Counting number of values with constrains.
   * Example:
   * Count number of string in a List of string that have length 4. 
   */
  
  val countingStrings = List("Sara", "Wojciech", "Lacie",
                              "Leyton", "Hunter", "Lincoln")
  println(countingStrings.count(s => s.length() == 4))
  println(countingStrings.count(s => s == "Wojciech"))
  
  // Return list without two first elements
  val withoutFirstTwo = notSoEmptyList.drop(2)
  println(withoutFirstTwo)
  
  // Return list without last two elements (2 elements from the right)
  val withoutLastTwo = notSoEmptyList.dropRight(2)
  println(withoutLastTwo)
  
  // Determine if an object exists in the List
  val ifExists = notSoEmptyList.exists(s => s == 1)
  println(ifExists)
  
  /*
   * Filter:
   * Return all elements that satisfy a constraint
   */
  val satisfyConstrain = countingStrings.filter(s => s.length >= 4)
  println(satisfyConstrain)
  
  
  /*
   * Indicate if all of the elements fulfil the constraint
   */
  var someWords = List[String]()
  someWords = "one" :: "two" :: "three" :: someWords
  
  println(someWords)
  val doesAllEndsWithEOrO = someWords.forall(x => (x.endsWith("e") | x.endsWith("o")))
  println(doesAllEndsWithEOrO)
  
  /*
   * Execute a function for each object in the list
   */
  println("Print for each element:")
  someWords.foreach(s => println(s))
  
  
  // Return first element of the list
  println("The first element:")
  println(someWords.head)
  
  // Return all but the last element of the list
  println("Everything but the last element:")
  println(someWords.init)
  
  // Return the last element of the list
  println("The last element of the list:")
  println(someWords.tail)
  
  // Indicate if the array is Empty
  val myEmptyList = Nil
  println(myEmptyList.isEmpty)
  
  // Number of records in the list
  println(myEmptyList.length)
  println(List(1, 2).length)
  
  
  
  
  
  
  
  
  
}