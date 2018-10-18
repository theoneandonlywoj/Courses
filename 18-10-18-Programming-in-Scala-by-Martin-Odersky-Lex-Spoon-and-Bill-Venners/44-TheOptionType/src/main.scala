object main extends App {
  val capitals = Map("France" -> "Paris",
      "Japan" -> "Tokyo")
  
  println(capitals get "France")
  println(capitals.get("North Pole"))
  
  def show(x: Option[String]): String = x match {
    case Some(s) => s
    case None => "?"
  }
  
  println(show(capitals.get("Japan")))
  println(show(capitals.get("North Pole")))
}