package learnscala.sleepingbarber

import akka.actor._
import akka.actor.ActorSystem

abstract class ShopEvent
case object BarberSleep extends ShopEvent
case object BarberSlept extends ShopEvent
case object ShopClose extends ShopEvent
case object ShopClosed extends ShopEvent

class Shop extends Actor {
  private val barber = context.system.actorOf(Props(new Barber(this)), "barber")
  private var waiting_ = 0
  private val MaxQueue = 4

  def receive = {
    case customer: Customer => guide(customer)
    case ShopClose => barber ! BarberSleep
    case BarberSlept => shutdown()
    case that => throw new Exception("[s]shop only receive customers or events, but got " + that)
  }

  def waiting = waiting_
  def waiting_=(value:Int) = {
    this.synchronized {
      waiting_ = value
    }
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

  private def shutdown() = {
    context.stop(self)
    println("[s] shop closes")
  }
}

