object main extends App {
  /*
   * Abstract types
   */
  class Food
  class Grass extends Food 
  
  abstract class Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
  }
  
  class Cow extends Animal {
    type SuitableFood = Grass
    override def eat(food: Grass): Unit =  {
      println("Moo!")
    }
  }
  val c = new Cow()
  c.eat(new Grass)
  
}