// Creating class called ChecksumAccumulator
class ChecksumAccumulator{
  // Variables inside classes are called fields
  private var sum = 0
  // Defining two methods: add and checksum
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}
object main extends App{
  var ca = new ChecksumAccumulator
  ca.add("1".toByte)
  println(ca.checksum())
}