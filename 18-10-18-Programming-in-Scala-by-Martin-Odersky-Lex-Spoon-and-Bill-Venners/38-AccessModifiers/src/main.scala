object AccessModifiers extends App {
  /*
   * Access modifiers:
   */
  // 1) Private members
  class Outer {
    class Inner {
      private def f() { println("f")}
      class InnerMost {
        f()
      }
    }
   /*
    * Line below will not run,
    * because it is defined private.
    */
    // (new Inner).f()
  }
  /*
   * 2) Protected members
   * Protected member can be accessed from subclasses of the class in which the member is defined.
   */
  class Super {
    protected def f(): Unit = { println("Protected f!") }
  }
  class Sub extends Super {
    f() // This is OK!
  }
  class Other {
    // f() is not accessible!
    // (new Super).f()
  }
  /*
   * 3) Public members
   * Every member not labeled "private" or "protected"
   * is public!
   */
  /*
   * 4) Scope of protection
   * Modifier "private[X]" or "protected[X]" result in
   * private or protected access "up to" X,
   * where X is a given package, class or singleton object.
   * "private[this]" == access only from the same object.
   */

  /*
   * 5) Companion object
   * A class shares all its access rights with its companion object
   * and vice versa.
   * An object can access all private members of its companion object.
   */
  class Rocket {
    import Rocket.fuel
    private def canGoHomeAgain = fuel > 20
  }
  object Rocket {
    private def fuel = 10
    def chooseStrategy(rocket: Rocket) {
      if (rocket.canGoHomeAgain)
        goHome()
      else
        pickAStar()
    }
    def goHome() {}
    def pickAStar() {}
  }
}