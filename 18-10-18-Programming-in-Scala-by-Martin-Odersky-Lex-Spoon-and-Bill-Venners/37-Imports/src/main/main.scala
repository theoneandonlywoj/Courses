package main
/*
 * Import from package fruits:
 * 1) Apple1 as McInthosh
 * 2) Apple2
 * 
 * Import from package vegetable:
 * 1) everything
 * 
 * Import from package meat:
 * 1) Everything, but object Chicken.
 */
import fruits.{Apple1 => McInthosh, Apple2}
import vegetables._
import meat.{Chicken => _, _}

object main extends App {
  McInthosh.printMe()
  val a = new Apple2()
  a.printMe()
  Pork.printMe()
}