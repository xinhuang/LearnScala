package learnscala.commandline

import scala.annotation.StaticAnnotation

case class Option(val longname: String) extends StaticAnnotation

object CommandLine {

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val optionSetter = new OptionSetter[T]

    for (i <- 1 to args.length / 2) {
      val optionName = args(i * 2 - 2).drop(2)
      val value = args(i * 2 - 1)

      optionSetter.setOption(optionName, value)
    }

  	optionSetter.option
  }
  
}
