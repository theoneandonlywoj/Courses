object main extends App {
  /*
   * Lists:
   * 1) Lists are immutable
   * 2) Lists have a recursive structure
   * 3) Lists are homogeneous - all elements of a list have the same type.
   * 4) Nil represents empty List
   * 5) All lists are build from two fundamental building blocks:
   *    Nil and cons "::"
   * 6) init and last take time proportionally to the length of the List
   */
  
  val fruit: List[String] = List("apples", "oranges", "pears")
  val fruit2: List[String] = "apples" :: ("oranges" :: ("pears" :: Nil))
  val fruit3: List[String] = "apples" :: "oranges" :: "pears" :: Nil
  
  val nums: List[Int] = List(1, 2, 3, 4)
  val nums2: List[Int] = 1 :: (2 :: (3 :: (4 :: Nil)))
  val nums3: List[Int] = 1 :: 2 :: 3 :: 4 :: Nil
  
  val diag3: List[List[Int]] = 
    List(
        List(1, 0, 0),
        List(0, 1, 0),
        List(0, 0, 1)
        )
  val empty: List[Nothing] = List()
  
  /*
   * Operations on lists:
   * 1) head - returns the first element of a list (only non-empty Lists)
   * 2) tail - returns a list consisting of all elements except the first (only non-empty Lists) 
   * 3) isEmpty - returns true is the list is empty
   * 4) init - returns a list consistin of all elements except the last (only non-empty Lists)
   * 5) last - returns the last element of a list (only non-empty Lists)
   */
  println(empty.isEmpty)
  println(fruit.isEmpty)
  
  println(fruit)
  println(fruit.head)
  println(fruit.tail)
  println(fruit.init)
  println(fruit.last)
  println(fruit.length)
  
  println(diag3.head)
  println(diag3.length)
  /*
   * List patterns:
   * Lists can be taken apart using pattern matching
   */
  val List(a, b, c) = fruit
  println(a)
  println(b)
  println(c)
  
  /*
   * Reverse:
   * 1) List(1, 2).reverse.reverse = List(1, 2)
   * 2) Operations equivalents:
   * x.reverse.init == x.tail.reverse
   * x.reverse.tail == x.init.reverse
   * x.reverse.head == x.last
   * x.reverse.last == x.head
   */
  val x = List(1, 2, 3, 4, 5)
  println(x.reverse)
  println(x.reverse.init)
  println(x.tail.reverse)
  println(x.reverse.tail)
  println(x.init.reverse)
  
  /*
   * Additional operations:
   * 1) take - returns first n elements of the List
   * 2) drop - returns all elements of the List expect the first n elements
   * 3) splitAt - returns two List split at n-th element.
   *   The first List will contain the n-th element.
   *   It returns a Tuple with two elements (List, List).
   * 4) Flatten - flatten list of lists (only for List[List[T]])
   */
  println(x)
  println(x take 3)
  println(x drop 3)
  println(x splitAt 3)
  println(List(List(1, 2, 3), List(4, 5, 6), List(7, 8)))
  println(List(List(1, 2, 3), List(4, 5, 6), List(7, 8)).flatten)
  /*
   * Zipping lists:
   * 1) Zip - takes two lists and returns a list of pairs
   * 2) Unzip
   * 3) zipWithIndex
   */
  val toZipList1: List[String] = List("a", "b", "c", "d", "e")
  val toZipList2: List[Int] = List(1, 3, 5, 7, 9)
  println(toZipList1)
  println(toZipList2)
  val zipped1: List[(String, Int)] = toZipList1 zip toZipList2
  val zipped2: List[(Int, String)] = toZipList2 zip toZipList1
  val zippedWithIndexList: List[(String, Int)] = toZipList1.zipWithIndex
  println(zipped1)
  println(zipped2)
  println(zipped2)
  
  val unzippedList: (List[String], List[Int]) = zippedWithIndexList.unzip
  println(unzippedList)
  println(unzippedList._1)
  println(unzippedList._2)
  
  /*
   * Displaying lists:
   * 1) toString
   * 2) mkString(pre, sep, post)
   */
  val abcde = List("a", "b", "c", "d", "e")
  println(abcde.mkString("!", ", ", "-"))
  
  /*
   * Converting lists:
   * 1) iterator
   * 2) toArray
   * 3) copyToArray
   */
  val arr = abcde.toArray
  println(arr.mkString)
  println(arr.toList)
  
  val arr2 = new Array[Int](10)
  println(arr2.mkString)
  /*
   * Copying to array:1
   * 1) It must fit in the array!
   * 2) Starts from given index f.e. 3.
   */
  List(1, 2, 3) copyToArray(arr2, 3)
  println(arr2.mkString)
  
  // Iterator
  val iter = abcde.iterator
  println(iter.next)
  println(iter.next)
}
