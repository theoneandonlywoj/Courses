object main extends App{
  val n = 13
  val half = 
    if (n % 2 == 0){
      n / 2
    }
    else{
      throw new RuntimeException("N must be even")
    }
}