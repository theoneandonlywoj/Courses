object main extends App {
  /*
   * Enumerations
   */
  object Directions extends Enumeration {
    val North, East, South, West = Value
  }
  for (d <- Directions.values){
    print(d + " ")
  }
  println(Directions.North.id)
}