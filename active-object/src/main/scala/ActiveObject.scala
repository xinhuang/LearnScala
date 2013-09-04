package learnscala.activeobject

import akka.actor.ActorSystem
import akka.actor.ActorDSL._
import akka.actor.PoisonPill

class ActiveObject {
  type Message = () => Unit

  private var isRunning = true
  private case class ActorStop()
  private implicit val system = ActorSystem("active-object")

  def send(m: Message) = this ! m
  def !(m: Message) = a ! m

  def stop() = {
    a ! ActorStop
    while (isRunning)
      Thread.sleep(100)
  }
  


  private val a = actor(new Act {
      become {
        case m: Message => m()
        case ActorStop => { 
          context.stop(self)
          isRunning = false
        }
      }
    })
}
