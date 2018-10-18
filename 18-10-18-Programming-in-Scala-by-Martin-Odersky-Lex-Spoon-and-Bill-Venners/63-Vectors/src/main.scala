import scala.collection.immutable.Vector
object main extends App {
  /*
   * Vectors:
   * Vectors is a collection type that provide
   * an effectively constant time to access its all elements,
   * unlike the List, where that changes depending on the size
   * of the List.
   * Time to access any element of a Vector is higher
   * than accessing the head of a List with the same components,
   * however, for big Lists this time could grow extremely large.
   * Vector provides an effectively constant time.
   * Vector deals with List's overhead by storing the information
   * as tree nodes, where each node contains of up to 32 elements.
   */
  val vec1 = Vector.empty
  val vec2 = vec1 :+ 1 :+ 2
  val vec3 = 100 +: vec2
  val vec4 = vec3 :+ 200
  println(vec1)
  println(vec2)
  println(vec3)
  println(vec4)
  println(vec4(0))
  /*
   * Vector are immutable, however it is possible to create
   * a new vector which differs from the old one by one value
   * with method updated.
   */
  val vec5: Vector[Int] = Vector(1, 2, 3)
  val vec6: Vector[Int] = vec5.updated(0, -1)
  println(vec5)
  println(vec6)
}