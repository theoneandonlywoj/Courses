object main extends App {
  /*
   * There are three types of variance:
   * 1) Covariance (lower bound)
   * 2) Contravariance (uppder bound)
   * 3) Invariance
   */
  
  /*
   * 1) Covariance
   * Example of a Vending machine, which can dispense only 
   */
  trait Drink
  class SoftDrink extends Drink
  class Juice extends Drink
  
  class Cola extends SoftDrink
  class TonicWater extends SoftDrink
  class OrangeJuice extends Juice
  class AppleJuice extends Juice
  
  class VendingMachine[+A]  
  // Installing the machine
  def install(softDrinkVM: VendingMachine[SoftDrink]): Unit = {
      println("Machine installed!")
    }
  // Invariance
  install(new VendingMachine[SoftDrink])
  /*
   * Covariance
   * The method "install" accepts parameter of type SoftDrink,
   * but since it is defined with type +T, it will accept
   * parameters of subtypes of SoftDrink - Cola and Tonic Water
   */
  install(new VendingMachine[Cola])
  install(new VendingMachine[TonicWater])
  /*
   *  The method will not accept the parameter of type
   *  VendingMachine[Drink], because Drink is not a subtype of 
   *  SoftDrink.
   */
  // install(new VendingMachine[Drink]
  
  /*
   * 2) Contravariance
   * The method will accept the parameter of type 
   * AmmaMagazine[ExplosiveBullet] and all classes it extends.
   */
  trait Bullet
  class NormalBullet extends Bullet
  class ExplosiveBullet extends NormalBullet
  
  class AmmoMagazine[-A]
  
  def emptyTheMagazine(bulletType: AmmoMagazine[ExplosiveBullet]){
    println("Magazine emptied!")
  }
  
  emptyTheMagazine(new AmmoMagazine[Bullet])
  emptyTheMagazine(new AmmoMagazine[NormalBullet])
}