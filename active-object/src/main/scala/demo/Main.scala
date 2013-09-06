package learnscala.activeobject.demo

import learnscala.raii.RAII._

object Program {
  def main(args: Array[String]): Unit = {
    using (new AsyncConsole()) { console =>
      for( i <- 0 to 100) {
        console.print(i)
      }
      println("\nasync print finished")
    }
  }
}
