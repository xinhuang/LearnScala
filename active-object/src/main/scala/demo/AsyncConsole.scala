package learnscala.activeobject.demo

import learnscala.activeobject.ActiveObject

class AsyncConsole {
  val activeObject = new ActiveObject()

  def print(o: Any): Unit = {
    activeObject ! (() => doPrint(o))
  }

  def stop(): Unit = activeObject.stop()

  private def doPrint(o: Any): Unit = {
    Console.print(o.toString)
  }
}
