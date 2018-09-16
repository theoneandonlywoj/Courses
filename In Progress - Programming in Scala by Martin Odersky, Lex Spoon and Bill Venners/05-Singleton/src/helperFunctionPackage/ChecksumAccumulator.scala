package helperFunctionPackage
import scala.collection.mutable

// Class from previous project example
class ChecksumAccumulator{
  // Variables inside classes are called fields
  private var sum = 0
  // Defining two methods: add and checksum
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}
/*
 * Singleton.
 * Singleton is a lazy first-class object.
 * 
 * A first class object is an entity that can be dynamically created, destroyed,
 * passed to a function, returned as a value,
 * and have all the rights as other variables in the programming language have. 
 * 
 * To some extend it can be compared to Java's class
 * containing static methods.
 * Singleton is an instance of the superclass
 * and it extends the superclass and is mixed in traits.
 * Singleton cannot take parameters, unlike the class.
 * It is initialised as a synthetic class when it is first time accessed. 
 * If a single has the same name as a class,
 * the class is called a companion object.
 * They need to be defined in the same file.
 * Both the companion class and the singleton
 * can access each others private members.
 * 
 * If the singleton and its superclass do not have the same name, 
 * the singleton is called a standalone object.
 *  
 */
//  
object ChecksumAccumulator{
  private val cache = mutable.Map[String, Int]()
  
  def calculate(s: String): Int = {
    if(cache.contains(s)){
      cache(s)
    }
    else{
      val acc = new ChecksumAccumulator
      for(c <- s){
        acc.add(c.toByte)
      }
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
  }
}

