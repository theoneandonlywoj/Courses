import scala.collection.mutable
object main extends App {
  val myMap = mutable.Map.empty[String, Int]
  // Adding entries method 1:
  myMap("hello") = 1
  myMap("there") = 2
  println(myMap)
  // Adding entries method 2:
  val myMap2 = myMap + ("hi!" -> 3)
  println(myMap2)
  // Adding entried method 3 (only for mutable Maps):
  var myMap3 = mutable.Map.empty[String, Int]
  println(myMap3)
  myMap3 += ("hi" -> 1)
  println(myMap3)
  myMap3 ++= mutable.Map("world" -> 5, "hi again" -> 6)
  println(myMap3)
  // Return keys of the Map (as Iterable)
  val myMapKeysIterable = myMap3.keys
  println(myMapKeysIterable)
  // Return keys of the Map (as Set)
  val myMapKeysSet = myMap3.keySet
  println(myMapKeysSet)
  // Return values (as Iterable)
  val myMapValuesIterable = myMap3.values
  println(myMapValuesIterable)
}