package learnscala.activeobject

import akka.actor.ActorSystem
import akka.actor.ActorDSL._
import akka.actor.PoisonPill

trait ActiveObject {
  type Message = () => Unit

  private var isRunning = true

  def send(m: Message) = this ! m
  def !(m: Message) = a ! m

  def stop() = {
    a ! PoisonPill
    while (isRunning)
      Thread.sleep(100)
  }
  
  private implicit val system = ActorSystem("active-object")

  case class ActorStop()

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
