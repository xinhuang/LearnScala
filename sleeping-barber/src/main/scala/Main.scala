package learnscala.sleepingbarber

import scala.util.Random
import akka.actor._
import akka.actor.ActorSystem

object Program {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val barber = system.actorOf(Props[Barber], name="barber")

    for( i <- 0 to 20) {
    	barber ! new Customer(i)
    }
  }		
}
