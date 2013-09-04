package learnscala.activeobject

import akka.actor.ActorSystem
import akka.actor.ActorDSL._

class ActiveObject {
  type Message = () => Unit
  case object StopEvent

  private var isRunning = true
  private implicit val system = ActorSystem("active-object")

  def send(m: Message) = this ! m
  def !(m: Message) = a ! m

  def stop() = {
    a ! StopEvent
    while (isRunning) {
      Thread.sleep(100)
    }
  }

  private val a = actor("active-objct") (new Act {
      become {
        case m: Message => m()
        case StopEvent => { 
          context.stop(self)
          isRunning = false
        }
      }
    })
}

