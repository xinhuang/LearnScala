package learnscala.sleepingbarber

import akka.actor._
import akka.actor.ActorSystem
import scala.util.Random

object Program {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sleeping-barber")
    val shop = system.actorOf(Props[Shop], "shop")
    val rand = new Random()

    system.registerOnTermination {
      println("system shutdown")
      system.shutdown()
    }

    for( i <- 0 to 20) {
    	Thread.sleep(rand.nextInt(450))
    	shop ! new Customer(i)
    }

    shop ! ShopClose
  }		
}
