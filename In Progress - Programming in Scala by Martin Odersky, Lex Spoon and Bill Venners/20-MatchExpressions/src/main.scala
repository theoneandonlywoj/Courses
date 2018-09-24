object main {
  def main(args: Array[String]){
    // A little bit brute approach
    val firstArg = 
      if (args.length > 0) args(0) else ""
    firstArg match {
        case "salt" => println("pepper")
        case "eggs" => println("bacon")
        case _ => println("huh?")
      }
    // Another approach
    val firstArg2: String = 
      firstArg match {
      case "salt" => "pepper"
      case "eggs" => "bacon"
      case _ => "huh?"
    }
    println(firstArg2)
  }
}