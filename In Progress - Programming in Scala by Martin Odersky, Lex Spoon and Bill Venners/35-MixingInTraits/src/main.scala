object main extends App {
  /*
   * Traits:
   * Traits encapsulate methods and fields and
   * can be mixed into classes.
   * A class can mix in multiple traits.
   * A class must can inheret just from one superclass.
   *  A trait cannot have "class" parameters
   *  (parameters passed to the primary class constructor).
   * A trait can be use to "enrich" classes by adding 
   * new methods defined in the the trait.
   */
  trait Philosophical {
    def philosophize(): Unit = {
      println("I consume memory, therefore I am!")
    }
  }
  
  class Frog extends Philosophical {
    override def toString: String = "green"
  }
  
  val frog = new Frog
  frog.philosophize()
  
  // Mixing in a trait using "with"
  class Animal
  class Frog2 extends Animal with Philosophical {
    override def toString: String = "green2"
  }
  // Mixing in multiple traits
  class Animal2
  trait HasLegs
  
  class Frog3 extends Animal2 with Philosophical with HasLegs {
    override def toString: String = "green3"
  }
  val frog3 = new Frog3
  println(frog3)
}