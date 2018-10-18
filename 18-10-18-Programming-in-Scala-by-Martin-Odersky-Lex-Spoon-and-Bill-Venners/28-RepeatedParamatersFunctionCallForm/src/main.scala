object main extends App{
  /*
   * Scala allows to define a function in such a way,
   * that the last parameter passed to the function
   * may be repeated.
   */
  def echo(args: String*){
    for (arg <- args) println(arg)
  }
  echo("hi")
  echo("hello", "World")
  
  def multipleEcho(i: Int, args: String*){
    var index = 0
    while(index < i){
      for (arg <- args) println(arg)
      index += 1
    }
  }
  multipleEcho(2, "multipleHi!")
  multipleEcho(2, "hi!", "hello")
  
  /*
   *  Passing an array
   */
  val arr = Array("one", "two", "three")
  /*
   * Notation "_*" tels the compiles to pass each element of Array arr 
   * as its own argument to function echo, rather than a bulk in form of a single Array[String]
   */
  echo(arr: _*)
  multipleEcho(2, arr:_*)
}