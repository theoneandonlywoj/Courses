object main extends App{
  // Sum
  val sum1 = 1.2 + 2.3
  println(sum1)
  println(sum1.getClass())
  
  // Substraction
  val substr1 = 3 - 1
  println(substr1)
  println(substr1.getClass())
  
  val substr2 = 'b' - 'a'
  println(substr2)
  println(substr2.getClass())
  
  // Multiplication
  val mult = 2L * 3L
  println(mult)
  println(mult.getClass())
  
  // Division
  val div1 = 11 / 4
  println(div1)
  println(div1.getClass())
  
  val div2 = 11.0f / 4.0f
  println(div2)
  println(div2.getClass())
  
  val div3 = 11f / 4
  println(div3)
  println(div3.getClass())
  
  // Remainder
  val remainder = 11 % 4
  println(remainder)
  println(remainder.getClass())
  
  // Remainder according to IEEE 754
  // It return a double!
  val remainderIEEE745 = math.IEEEremainder(11, 4)
  println(remainderIEEE745)
  println(remainderIEEE745.getClass())
  
}