object main extends App{
  /*
   * Processed string literal:
   * To utilise Scala's string interpolation capabilities
   * the interpolated string need to be preceeded by letter s.
   */
  val name = "reader"
  val message = s"Hello, $name!"
  println(message)
  
  /*
   * Raw interpolation:
   * Raw interpolation does not recognise character literal escape characters.
   */
  
  println(raw"No \\\ escape!")
  
  /*
   * F string interpolation:
   * F interpolation allows style formatting, including decimals (for numbers).
   */
  val pi = "Pi"
  val message2 = f"$pi is approximately ${math.Pi}%.8f."
  println(message2)
}