package learnscala.sleepingbarber

import scala.util.Random

class Barber {
  private val SeatTotal = 3
  private val random = new Random()

  def shear(customer: Customer) {
    println("[b] cutting hair of " + customer)
    Thread.sleep(100 + random.nextInt(400))
    customer.shorn = true
  }
}
