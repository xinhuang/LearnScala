package learnscala.sleepingbarber

import scala.util.Random
import akka.actor._

class Barber(private val shop: Shop) extends Actor {
  private val SeatTotal = 3
  private val random = new Random()

  private def shear(customer: Customer) {
    println("[b] cutting hair of " + customer)
    Thread.sleep(100 + random.nextInt(400))
    customer.shorn = true
    
    shop.waiting -= 1
  }

  def receive = {
  	case customer: Customer => shear(customer)
  }
}
