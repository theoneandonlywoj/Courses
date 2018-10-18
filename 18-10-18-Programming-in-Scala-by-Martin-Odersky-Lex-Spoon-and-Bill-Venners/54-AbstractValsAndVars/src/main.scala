object main extends App {
  /*
   * Abstract vals:
   * - Should be implemented as vals
   * instead of defined methods to ensure
   * the logic is not overwritten by the subclass.
   */
  trait Buffer {
    // Defining an abstract type T
    type T
    val element: T
  }
  abstract class SeqBuffer extends Buffer {
    type U
    // Specifying that type T is 
    type T <: Seq[U]
    def length = element.length
  }
  abstract class IntSeqBuffer extends SeqBuffer {
    type U = Int
  }
  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
    new IntSeqBuffer {
         type T = List[U]
         val element = List(elem1, elem2)
       }
  val buf = newIntSeqBuf(1, 2)
  println(buf.length)
  println(buf.element)
  
  /* Abstract vars:
   * - Should be implemented in the abstract class 
   * with getters and setters
   * - Setters should be defined as methods without brackets
   * with trailing underscore
   * f.e. def length_: Int = element.length
   * - Getters should be defined as methods without brackets
   * with the same name as the variable.
   */
  trait AbstractTime {
    def hour: Int
    def hour_ = (hour: Int)
    def minute: Int
    def minute_ = (minute: Int)
  }
  class Time(var hour: Int, var minute: Int) extends AbstractTime {
    def printTime(): Unit = {
      println(hour + ":" + minute)
    }
  }
  val t = new Time(13, 40)
  t.printTime()
}