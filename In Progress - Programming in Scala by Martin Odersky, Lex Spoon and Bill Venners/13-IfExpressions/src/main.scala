 /*
 * Presenting good style of assigning a values to a variable
 * with an if-statement.
 * 
 * Usage:
 * scala main.scala myFile.txt
 * or 
 * scala main.scala
 */
var filename = 
  if(!args.isEmpty) args(0)
  else "default.txt"
println(filename)
