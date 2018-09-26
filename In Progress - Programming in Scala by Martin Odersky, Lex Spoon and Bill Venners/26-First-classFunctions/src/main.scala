object main extends App{
  /*
   * Scala has first-class functions.
   * That means that functions are treated in the same manner as variables.
   * Thus, a function can be passed to another function as an argument.
   * A function value is a function literal, which is compiled into a class 
   * (extends one of serveral FunctionN traits) when instantiated at runtime.
   * A function value can be store in a variable.
   */
  // Example of function value
  var increase = (x: Int) => x + 1
  println(increase(10))
  // Variable "increase" is a variable, thus it can be re-assigned.
  increase = (x: Int) => x + 9990
  println(increase(10))
  
  increase = (x: Int) => {
    println("Increment by one!")
    x + 1
  }
  println(increase(1))
}