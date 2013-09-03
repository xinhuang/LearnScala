package learnscala.sleepingbarber

import akka.actor.Actor
import scala.util.Random

class Barber extends Actor {
  private val SeatTotal = 3
  private val random = new Random()

  def serve(customer: Customer) {
    println("[b] cutting hair of " + customer)
    Thread.sleep(100 + random.nextInt(400))
    customer.shorn = true
  }

  def receive = {
    case customer: Customer => serve(customer)
  }
}
