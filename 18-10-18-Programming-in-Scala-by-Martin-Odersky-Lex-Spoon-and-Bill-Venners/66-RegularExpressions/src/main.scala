import scala.util.matching.Regex
object main extends App {
  /*
   * Regular expressions
   * Searching for regular expressions:
   * 1) myRegexPattern.findFirstIn(myStr) - find first occurrence
   * of the pattern in the String myStr returning Option
   * 2) myRegexPattern.findAllIn(myStr) - find all occurrences
   * of the pattern in the String myStr returning Iterator
   * 3) myRegexPattern.findPrefixOf(myStr) - find an occurrence
   * of the pattern at the start of String myStr returning Option
   */
  val myRegexPatternDecimal: Regex = new Regex("(-)?(\\d+)(\\.\\d*)?")
  val myStr: String = "for -1 to 99 by 3"
  
  println(myRegexPatternDecimal.findFirstIn(myStr))
  for (s <- myRegexPatternDecimal.findAllIn(myStr))
    println(s)
  println(myRegexPatternDecimal.findPrefixMatchOf("3.14 is an approximation of Pi."))
  /*
   * Every regex defines an extractor that returns number of Strings
   * matching number of groups of the regular expression.
   */
  val myRegexPatternDecimal(sign: String,
      integerPart: String,
      decimalPart: String) = "-3.14"
  println(sign)
  println(integerPart)
  println(decimalPart)
}