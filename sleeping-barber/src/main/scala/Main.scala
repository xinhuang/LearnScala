package learnscala.sleepingbarber

import akka.actor._
import akka.actor.ActorSystem

object Program {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val shop = system.actorOf(Props[Shop])

    for( i <- 0 to 20) {
    	shop ! new Customer(i)
    }
  }		
}
