package learnscala.activeobject.demo

import learnscala.activeobject._

class AsyncConsole {
  val activeObject = new ActiveObject()

  def print(o: Any): Future[Unit] = {
    activeObject ! (() => doPrint(o))
  }

  def stop(): Unit = activeObject.stop()

  def dispose() = stop()

  private def doPrint(o: Any): Unit = {
    Console.print(o.toString)
  }
}
