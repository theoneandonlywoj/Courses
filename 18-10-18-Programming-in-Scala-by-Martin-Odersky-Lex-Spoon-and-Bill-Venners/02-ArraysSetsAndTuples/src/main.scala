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
   * Filter Not:
   * Return all elements that do not satisfy a constraint
   */
  val doNotSatisfyConstraing = countingStrings.filter(s => s.length >= 4)
  println(doNotSatisfyConstraing)
  
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
  println(someWords.last)
  
  // Return all but the first element of the list
  println("Everything but the first element:")
  println(someWords.tail)
  
  // Indicate if the array is Empty
  val myEmptyList = Nil
  println(myEmptyList.isEmpty)
  
  // Number of records in the list
  println(myEmptyList.length)
  println(List(1, 2).length)
    
  // Map
  val addingLetterL = someWords.map(s => s + "l")
  println(addingLetterL)
  
  val returnLengthTimesTwo = someWords.map(s => s.length * 2)
  println(returnLengthTimesTwo)
  
  /*
   * Reverse:
   * Return the list in the reversed order
   */
  val notReversed = List("FirstWord", "SecondWord", "ThirdWord")
  println(notReversed)
  
  val listReversed = notReversed.reverse
  println(listReversed)
  
  /*
   * Sorting in a specific order.
   * Example:
   * Sorting by the first character of the word
   */
  val exampleWords = List("bear", "dog", "cat")
  println(exampleWords)
  val someWordsSorted = exampleWords.sortWith((s, t) => 
                            s.charAt(0).toLower < t.charAt(0).toLower)
  println(someWordsSorted)
  
  /*
   * Make a string from the list.
   */
  
  println(exampleWords.mkString(", "))
  println(exampleWords.mkString(":"))
  
  /*
   * Tuples.
   * Tuples are immutable, but can contain objects of different types.
   * Creating tuples and obtaining elements from them.
   */
  val myNewTuple = ("Something", "Something2")
  
  // Accessing the first element of the tuple
  val myNewTupleFirstValue = myNewTuple._1
  
  // Accessing the second element of the tuple
  val myNewTupleSecondValue = myNewTuple._2
  
  println(myNewTupleFirstValue)
  println(myNewTupleSecondValue)
  
  /*
   * Sets:
   * In Scala, sets can be mutable or and immutable.
   * Both mutable and immutable version of the set can be imported
   * from Scala's collection.
   */
  
  // Mutable Sets
  import scala.collection.mutable
  
  val movieSet = mutable.Set("Inception", "Matrix")
  movieSet += "Batman"
  println(movieSet)
  
  // Immutable Sets
  import scala.collection.immutable
  
  val immutableMovieSet = immutable.Set("Value1", "Value2")
  println(immutableMovieSet)
  
  /*
   * Maps:
   * In Scala, maps can be mutable or immutable.
   * Both mutable and immutable version of the map can be imported
   * from Scala's collection. 
   */
  
  // Mutable maps
  import scala.collection.mutable
  
  val treasureMap = mutable.Map[Int, String]()
  treasureMap += (1 -> "Go to island.")
  treasureMap += (2 -> "Find big X on the ground")
  treasureMap += (3 -> "Dig.")
  println(treasureMap(2))
  
  // Immutable maps are the default option, the import is not necessary
  // Just for clarity:
  import scala.collection.immutable
  
  val romanNumeral = immutable.Map(1 -> "I",
                                   2 -> "II",
                                   3 -> "III",
                                   4 -> "IV",
                                   5 -> "V")
  println(romanNumeral(4))
}