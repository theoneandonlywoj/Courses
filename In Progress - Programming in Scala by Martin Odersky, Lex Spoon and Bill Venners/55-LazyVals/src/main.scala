object main extends App {
  /*
   * Lazy vals:
   * Lazy vals are initialised at the point when they are used.
   * They are defined using the "lazy" modifier.
   */
  object Demo1 {
    val x: String = {
      println("initializing x")
      "done"
    }
  }
  /*
   * Value x is initialised when object Demo is called.
   */
  println("Initializing Demo1")
  println(Demo1)
  println("Now x...")
  println(Demo1.x)
  /*
   * Value x is initialized when it is called,
   * not when the object Demo is initialised.
   */
  object Demo2 { 
    lazy val x: String = {
      println("lazy initializiation of x")
      "done"
    }
  }
  println("Initializing Demo2")
  println(Demo2)
  println("Now x...")
  println(Demo2.x)
}