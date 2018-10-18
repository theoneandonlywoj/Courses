import helperFunctionPackage.ChecksumAccumulator

object main {
  def main(args: Array[String]) {
    val sum = ChecksumAccumulator.calculate("hello")
    val sum2 = ChecksumAccumulator.calculate("hello, World")
    
    println("hello [" + sum + "]")
    println("hellowWorld [" + sum2 + "]")
  }
}