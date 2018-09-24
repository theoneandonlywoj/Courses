object main extends App{
  /*
   * Scope in loops
   */
  var i = 1
  // Only is in scope here
  while (i <= 10){
    var j = 1
    // both i and j are in scope here
     while (j <= 10){
       /*
        *  Defining local value 'prod'
        *  Every time the function is invoked,
        *  a new set of variables / values are used.
        */
       
       val prod = (i * j).toString
       // i, j, and prod are in scope here
       var k = prod.length
       // i, j, prod, and k in scope here
       while (k < 4){
         print(" ")
         k += 1
       }
       print(prod)
       j += 1
     }
    println()
    i += 1
  }
}