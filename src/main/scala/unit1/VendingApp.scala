package unit1

object VendingApp {
  @main def run(): Unit = {
    val machine = new VendingMachine
    println(machine.buy("chocolate", 1.5))
    println(machine.chocolateBar)
    machine.chocolateBar += 2
    machine.granolaBar += 1
    println(s"Total money: ${machine.totalMoney}")
    println(machine.buy("chocolate", 1.5))
    println(s"Total money: ${machine.totalMoney}")
    println(machine.buy("chocolate", 1))
  }
}
