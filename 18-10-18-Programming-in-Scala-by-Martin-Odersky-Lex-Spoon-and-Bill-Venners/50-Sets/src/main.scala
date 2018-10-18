import scala.collection.immutable.{Set => Set}
import scala.collection.mutable.{Set => MutableSet}
object main extends App {
  /*
   * Sets:
   * Sets can contain of only unique elements.
   */
  val nums: Set[Int] = Set(1, 2, 3)
  println(nums)
  // Add an element
  val nums2 = nums + 5
  println(nums2)
  // Add multiple elements
  val nums3 = nums2 ++ List(5, 6)
  println(nums3)
  // Remove a specific element
  val nums4 = nums2 - 3
  println(nums4)
  // Remove multiple elements
  val nums5 = nums2 -- List(3, 5)
  println(nums5)
  // Intersection of two sets
  println(Set(1, 2, 3) & Set(2, 3, 4))
  // Size of the set
  println(Set(1, 2, 3).size)
  // Check if the set contains a value
  println(Set(1, 3, 5).contains(3))
  println(Set(1, 3, 5).contains(2))
  // Create an empty Set
  println(MutableSet.empty[String])
  // Clear the set = Empty
  println(MutableSet(1, 5, 7).clear)
}