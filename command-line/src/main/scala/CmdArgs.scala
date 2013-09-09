package learnscala.commandline

import scala.language.dynamics
import scala.collection.mutable.Map

class CmdArgs extends Dynamic {
  private val vars = Map.empty[String, Any]

  def selectDynamic(name: String): Any = 
    vars.get(name).getOrElse(sys.error("method not found: " + name))

  def updateDynamic(name: String)(value: Any): Unit =
    vars += name -> value
}
