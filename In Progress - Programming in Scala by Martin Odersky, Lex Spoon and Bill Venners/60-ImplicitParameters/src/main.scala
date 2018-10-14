object main extends App {
  /*
   * Implicit parameters:
   * Keyword "implicit" applies to an entire parameter list,
   * not to individual parameters (all within given brackets).
   * The compiler selects implicits by type matching.
   * Thus, in order to eliminate unwanted behavior,
   * implicit, usually have "special" types.
   * In case when multiple (that are in the scope) would work,
   * Scala refuses to insert a conversion.
   * The compiler will complain about the implicit conversion being
   * ambiguous.
   * Because of that, implicits must be defined in as boilerplate
   * form as possible.
   */
  class PreferredPrompt(val preference: String)
  object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt): Unit = {
      println("Welcome, " + name + ". The system is ready.")
      println(prompt.preference)
    }
  }
  val bobsPrompt: PreferredPrompt = 
      new PreferredPrompt("relax> ")
  Greeter.greet("Bob")(bobsPrompt)
  
  // Letting the compiler to fill the parameter implicitly
  object JoesPrefs {
    implicit val prompt: PreferredPrompt = new PreferredPrompt("Yes, master> ")
  }
  // Importing the implicit value into the scope
  import JoesPrefs._
  Greeter.greet("Joe")
}