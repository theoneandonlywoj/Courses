object main extends App {
  /*
   * In many Java libraries (like f.e. JavaBeans) 
   * get and set operations are encapsulated
   * in methods getters and setters.
   * For a given variables v,
   * the getter is defined as "v",
   * and the setter as "v_".
   */
  class Time {
    private[this] var h: Int = 12
    private[this] var m: Int = 0
    
    def hour: Int = h
    def hour_: (x: Int): Unit = {
      require(0 < x && x < 24)
      h = x
    }
    
    def minute = m
    def minute_ (x: Int): Unit = {
      require(0 < x && x < 60)
      m = x
    }
  }
  
  val t = new Time
  println(t.minute)
  t.minute_(50)
  println(t.minute)
}