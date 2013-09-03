package learnscala.sleepingbarber

import akka.actor._
import akka.actor.ActorSystem

object ShopEvent extends Enumeration {
  val CustomerLeft = 0
}

class Shop(system: ActorSystem) extends Actor {
  private val barber = system.actorOf(Props(new Barber(this)))
  private var waiting_ = 0
  private val MaxQueue = 4

  def receive = {
    case customer: Customer => guide(customer)
    case ShopEvent.CustomerLeft => waiting -= 1
    case _ => throw new Exception("[s]shop only receive Customer")
  }

  private def guide(customer: Customer) = {
    if (waiting >= MaxQueue) {
      turnOver(customer)
    } else {
      waiting += 1
      barber ! customer
    }
  }

  private def turnOver(customer: Customer) = {
    println("[s] turn over " + customer)
  }

  def waiting = waiting_

  def waiting_=(value:Int) = {
    this.synchronized {
      waiting_ = value
    }
  }
}

