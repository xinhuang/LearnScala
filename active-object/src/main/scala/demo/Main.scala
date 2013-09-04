package learnscala.activeobject.demo

object Program {
  def main(args: Array[String]): Unit = {
    var console = new AsyncConsole()
    for( i <- 0 to 100) {
      console.print(i)
    }
    console.stop()
  }
}
