/*
 * Scala Standalone Application.
 * Extending the "main" object with App trait.
 */
object main extends App {
  for(season <- List("fall", "winter", "summer", "spring")){
    println(season)
  }
}