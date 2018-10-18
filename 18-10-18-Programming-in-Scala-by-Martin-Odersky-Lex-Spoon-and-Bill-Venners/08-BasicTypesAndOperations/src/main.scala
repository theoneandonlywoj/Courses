object main extends App{
  /*
   * Integral types:
   * 1) Byte - 8-bit signed two’s complement integer (from (-2^7) to (2^7-1), inclusive)
   * 2) Short - 16-bit signed two’s complement integer (from (-2^15) to (2^15-1), inclusive)
   * 3) Int - 32-bit signed two’s complement integer (from (-2^31) to (2^31-1), inclusive)
   * 4) Long - 64-bit signed two’s complement integer (from (-2^63) to (2^63-1), inclusive)
   * 5) Char - 16-bit unsigned Unicode character (from 0 to (2^16-1), inclusive)
   * 
   * Integral types with two types below are called numerical types:
   * 6) Float - 32-bit IEEE 754 single-precision float
   * 7) Double - 64-bit IEEE 754 double-precision float
   * 
   * Additional types are:
   * 8) Boolean - true or false
   * 9) String - a sequence of Char s
   * 
   * Literals:
   * A literal is a notation for representing a fixed value in source code.
   * 
   * Integer literals:
   * Integer literals for the types Int, Long, Short, and Byte come in two forms:
   * 1) decimal
   * 2) hexadecimal
   * 
   * Hexadecimal:
   * The way an integer literal begins indicates the base of the number.
   * If the number begins with a 0x or 0X , it is hexadecimal (base 16),
   * and may contain 0 through 9 as well as upper or lowercase digits A through F.
   */
  
  val hex1 = 0x5
  println(hex1)
  
  val hex2 = 0x00FF
  println(hex2)
  
  /*
   * If the number begins a non-zero digit and is undecorated,
   * it is decimal with base 10.
   */
  val dec1 = 31
  println(dec1)
  println(dec1.getClass())

  /*
   * If an integer literal ends with L or l, it is a Long.
   */
  val long1 = 0XCAFEBABEL
  println(long1)
  
  val long2 = 35L
  println(long2)
  println(long2.getClass())
  
  /*
   * If an integer literal is assigned to a variable 
   * of type Short or Byte, it is treated as one
   * as long as it is within limits specified for the type.
   */
  
  val short1: Short = 367
  println(short1)
  println(short1.getClass())
  
  val byte1: Byte = 36
  println(byte1)
  println(byte1.getClass())
  
  /*
   * Floating point literals:
   * Floatining point literals consists of:
   * 1) decimal digits
   * 2) optionally a decimal points
   * 3) optionally a followed by E or e and an exponent
   * 
   * Decimal digit part is multiplied
   * by the power of 10 of the exponent.
   * 
   * If a floating-point literal ends with F or f, it is a Float.
   * Otherwise, it is a Double.
   * Optionally, to be very specifid, a Double can end with D or d.
   */
  val float1 = 1.2345F
  println(float1)
  println(float1.getClass())
  
  val float2 = 1.2345e5f
  println(float2)
  println(float2.getClass())
  
  val double1 = 3e5
  println(double1)
  println(double1.getClass())
  
  val double2 = 3e2D
  println(double2)
  println(double2.getClass())
  
  /*
   * Character literals:
   * Character literals are composed of any Unicode character between single quotes.
   * On top of that, they can be specified using Unicode code point with backslach and u
   * with four hex digits.
   */
  
  val a = 'A'
  println(a)
  println(a.getClass())
  
  val unicodedD = '\u0044'
  println(unicodedD)
  println(unicodedD.getClass())
  
  /*
   * Special character literal escape sequences:
   * 1) \n = \u000A - line feed 
   * 2) \b = \u0008 - backspace 
   * 3) \t = \u0009 - tab
   * 4) \f = \u000C - form feed
   * 5) \r = \u000D - carriage return
   * 6) \" = \u0022 - double quote
   * 7) \' = \u0027 - single quote
   * 8) \\ = \u0027 - backslash
   */
 
  val backslash = '\\'
  
  println(backslash)
  println(backslash.getClass())
  
  /*
   * String literals:
   * A String literals contains of characters encapsulated in double quotes.
   * Scala provides syntax for dealing with raw string, which can contain
   * any characters, new lines, special characters and quotations marks.
   * The String must be surrounded by three double quotation marks 
   * (both at the beginning of the String and the end).
   * Sometimes that leads to awkward spacing.
   * Scala provides a stripMargin method
   * that combined with a pipe character |, helps with formatting.
   */
  println("""Welcome to the Jungle.
             You are going to die!
    """)
  println("""|Welcome to the Jungle.
             |You are going to die!""".stripMargin)  
  /*
   * Boolean literals:
   * Boolean type can hav only two values: true or false.
   */
    
  val bool1 = true
  println(bool1)
  println(bool1.getClass())
   
}