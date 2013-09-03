package learnscala.sleepingbarber

import akka.actor._
import akka.actor.ActorSystem
import scala.util.Random

object Program {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val shop = system.actorOf(Props(new Shop(system)))
    val rand = new Random()

    for( i <- 0 to 20) {
    	Thread.sleep(rand.nextInt(450))
    	shop ! new Customer(i)
    }
  }		
}
