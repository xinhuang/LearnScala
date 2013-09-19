package learnscala.commandline

import scala.annotation.StaticAnnotation

case class Option(val name: String, val abbr: String) extends StaticAnnotation {
  def this(name: String) = this(name, "")
}

object CommandLine {

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val optionSetter = new OptionSetter[T]

    var prev = ""
    val groups = args.groupBy{ arg => prev = optionGroup(arg, prev); prev }
    groups.map{ o => (o._1, o._2.drop(1)) }.foreach{o => 
      if (o._2.length == 1)
        optionSetter.setOption(o._1, o._2(0))
      else if (o._2.length == 0)
        optionSetter.setOption(o._1, "true")
    }

  	optionSetter.option
  }
  
  private def optionGroup(arg: String, prevOption: String): String = {
    if (arg.startsWith("--")) 
      arg.drop(2)
    else if (arg.startsWith("-"))
      arg.drop(1)
    else
      prevOption
  }
}
