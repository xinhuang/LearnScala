package learnscala.sleepingbarber

import akka.actor._

class Shop extends Actor {
  private val barber = new Barber()
  private var waiting = 0
  private val MaxQueue = 3

  def receive = {
    case customer: Customer => guide(customer)
    case _ => throw new Exception("[s]shop only receive Customer")
  }

  private def guide(customer: Customer) = {
    waiting += 1
    if (waiting > MaxQueue) {
      turnOver(customer)
    } else {
      barber shear customer
      waiting -= 1
    }
  }

  private def turnOver(customer: Customer) = {
    println("[s] turn over " + customer)
  }
}
