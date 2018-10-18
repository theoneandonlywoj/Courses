object main extends App{
  /*
   * An abstract class:
   * 1) defines methods that enables inheriting subclass to use
   * 2) defines absract methods that requires implementation in subclass 
   * 3) provides a common interface that allows interchanges among subclasses
   * 
   * "Abstract" modifier suggest that the class may have members without an implementation.
   * An abstract class cannot be instantiate.
   * 
   */
  abstract class Element{
    /*
     * Functions like:
     * 	def height():
     * are called empty-paren methods.
     * In Scala, it is prefered to define parameterless methods:
     * 	def height:
     * 
     * It is recommend to write pure function as parameterless methods.
     * They have no side-effects and often return attributes.
     * Functions having side effect and performing an operation 
     * are recommended to be written as empty-paren methods.
     */
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  }
  
  /*
   * Extending classes:
   */
  class ArrayElement(conts: Array[String]) extends Element{
    def contents: Array[String] = conts
  }
  
  val ae: ArrayElement = new ArrayElement(Array("hello", "arcyfelix"))
  println(ae.width)
  
  /*
   * Invoking superclass constructors
   */
  class LineElement(s: String) extends ArrayElement(Array(s)){
    override def width = s.length
    override def height = 1
  }
  val line: LineElement = new LineElement("hi")
  println(line.width)
  println(line.contents(0))
  
  /*
   * Subtyping:
   * Since class ArrayElements extends Element,
   * a new ArrayElement can be of type Element.
   */
  val e: Element = new ArrayElement(Array("hello", "World"))
  /*
   * Variables and expressions are dynamically bound.
   * That means, despite the fact a variable is 
   * f.e. of type Element, the implementation is based 
   * on the object type (invoked with "new"). 
   * Example of the dynamic binding:
   */
  abstract class Element2 {
    def demo(): Unit = {
      println("Element2's implementation is invoked!")
    }
  }
  
  class ArrayElement2 extends Element2 { 
    override def demo(): Unit = {
      println("ArrayElement2's implementation is invoked!")
    }
  }
  class LineElement2 extends ArrayElement2 { 
    override def demo(): Unit = {
      println("LineElement's implementation is invoked!")
    }
  }
  
  class UniformElement extends Element2{
    def invokeDemo(e: Element2) = {
      e.demo()
    }
  }
  
  val u = new UniformElement
  u.invokeDemo(new ArrayElement2)
  u.invokeDemo(new LineElement2)
  /*
   * UniformElement does not override
   * the demo() method.
   */
  u.invokeDemo(new UniformElement)
  
  /*
   * It is possible to ensure that a method 
   * is not override by the subclass, by using final.
   */
  
  /*
   * GridElement
   */
  abstract class GridElement {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  def above(that: GridElement): GridElement = {
    // ++ results in concatenation of the arrays
    new GridArrayElement(this.contents ++ that.contents)
  }
  def beside(that: GridElement): GridElement = {
    new GridArrayElement(
    for ((line1, line2) <- this.contents zip that.contents)
      yield line1 + line2
      )
  }
  override def toString = contents mkString "\n"
  }
  
  class GridArrayElement(conts: Array[String]) extends GridElement{
    def contents: Array[String] = conts
  }
  class GridLineElement(s: String) extends GridArrayElement(Array(s)){
    override def width = s.length
    override def height = 1
  }
  
  /*
   * Hiding implementation of GridElement behind a factory object.
   */
  object GridElement {
    def elem(contents: Array[String]): GridElement = 
      new GridArrayElement(contents)
    def elem(line: String): GridElement = 
      new GridLineElement(line)
  }
  
  val column1 = GridElement.elem("***") above GridElement.elem("+++")
  val column2 = GridElement.elem("---") above GridElement.elem("***") 
  println(column1 beside column2)
}