package learnscala.activeobject

import scala.concurrent._
import ExecutionContext.Implicits._
import java.util.concurrent.LinkedBlockingQueue

class ActiveObject { self =>
  type Message = () => Unit

  private var disposed = false

  private var done: Boolean = false
  private val queue = new LinkedBlockingQueue[Message]()
  private val thread = future {
    while (!done) {
      val message = queue.take
      message()
    }
  }

  def send(m: Message) = this ! m
  def !(m: Message) = queue.put(m)

  def stop(): Unit = {
    this ! (() => done = true)
    while (!thread.isCompleted) {
      Thread.sleep(100)
    }
  }

  def dispose(): Unit = {
    if (disposed)
      throw new Exception("object already disposed")
    stop()
    disposed = true
  }
}
