package learnscala.activeobject

import akka.actor.ActorSystem
import akka.actor.ActorDSL._

class ActiveObject {
  type Message = () => Unit
  case object StopEvent

  private var isRunning = true
  private var disposed = false
  private implicit val system = ActorSystem("active-object")

  def send(m: Message) = this ! m
  def !(m: Message) = a ! m

  def stop(): Unit = {
    a ! StopEvent
    while (isRunning) {
      Thread.sleep(100)
    }
  }

  def dispose(): Unit = {
    if (disposed)
      throw new Exception("object already disposed")
    stop()
    disposed = true
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

