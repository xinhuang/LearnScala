package learnscala.activeobject.demo

import learnscala.raii.using
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Program {
  def main(args: Array[String]): Unit = {
    using (new AsyncConsole()) { console =>
      for( i <- 0 to 100) {
        console.print(i)
      }
      println("\nasync print finished")
    }

    println()

    using (new AsyncConsole()) { console =>
      for( i <- 0 to 100) {
        Await.ready(console.print(i), Duration.Inf)
      }
      println("\nsync print finished")
    }

    println()
  }
}
