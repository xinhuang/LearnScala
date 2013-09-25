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

  def send[T](m: () => T): Future[T] = this ! m

  def ![T](m: () => T): Future[T] = {
    val p = promise[T]
    queue.put(() => execute(m, p))
    p.future
  }

  def stop(): Unit = {
    this ! (() => done = true)
    while (!thread.isCompleted) {
      Thread.sleep(100)
    }
  }

  private def execute[T](m: () => T, p: Promise[T]): Unit = {
    try {
      p success m()
    } catch {
      case e: Throwable => p failure e
    }
  }

  def dispose(): Unit = {
    if (disposed)
      throw new Exception("object already disposed")
    stop()
    disposed = true
  }
}
