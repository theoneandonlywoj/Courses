/*
 * Scala Standalone Application.
 * Extending the "main" object with App trait.
 * This solution has its shortcoming:
 * 1) Command-line arguments are unavailable.
 * 2) Multi-threating is unavailable because of JVM's limitations.
 * 3) JVM is not optimising initialisation of the code.
 * 
 * This approach should be used only is the program is fairly simple.
 */
object main extends App {
  for(season <- List("fall", "winter", "summer", "spring")){
    println(season)
  }
}