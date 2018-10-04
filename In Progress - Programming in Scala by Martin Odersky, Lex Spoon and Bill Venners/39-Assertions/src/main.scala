object main extends App {
  /*
   * Assertions
   */
  class MyElement(val width: Int, val height: Int)
  val e = new MyElement(1, 2)
  println(e.width)

  // Assert the elements have same width
  def assertSameWidth(elem1: MyElement, elem2: MyElement): Unit = {
    assert(elem1.width == elem2.width)
    println("There two elements are of the same width!")
  }
  def expandWidthByValue(elem: MyElement, myValue: Int): MyElement = {
    val newElem = new MyElement(width = elem.width + 1, height = elem.height)
    if (myValue > 0) {
      newElem
    } else {
      elem
    } ensuring (newElem.width >= elem.width)
  }

  var e1 = new MyElement(width = 1, height = 2)
  val e2 = new MyElement(width = 1, height = 3)

  assertSameWidth(e1, e2)
  println(e1.width)
  e1 = expandWidthByValue(elem = e1, 0)
  println(e1.width)

}