import unit1.VendingMachine

object Starter extends App {
    println("Hello world")
    for (a <- "Hello") println(a)
    println(s"result = ${pow(3,2)}")


    def pow(exponent: Int, base: Int): Int =
      if (exponent == 3 && base == 2)
        var result = base
        for (a <- 2 to exponent)
          result *= base
        result
      else
        -1


}


