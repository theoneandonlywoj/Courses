/*
 * Single line print function with a while loop
 * Run using: 
 * scala _04_SingleLinePrint.scala Scala is awesome
 */

var i = 0
while (i < args.length){
  if (i != 0){
    print(" ")
  }
  print(args(i))
  i += 1
}