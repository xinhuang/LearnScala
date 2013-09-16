package learnscala.commandline

import scala.reflect.runtime.universe
import scala.reflect.Manifest
import scala.annotation.StaticAnnotation

case class Option(val longname: String) extends StaticAnnotation

object CommandLine {
  private val rm = universe.runtimeMirror(getClass.getClassLoader)

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val option = m.runtimeClass.newInstance.asInstanceOf[T]

    val optionName = args(0).drop(2)
    val value = args(1)

    val im = rm.reflect(option)
    val field = findField[T](optionName)
    setField(im, field, value)

  	option
  }

  private def setField(im: universe.InstanceMirror, field: universe.TermSymbol, value: String) {
    val fm = im.reflectField(field)
    fm.set(value)
  }

  private def findField[T](optionName: String)(implicit m: Manifest[T]): universe.TermSymbol = {
    universe.typeOf[T].members.filter(m => !m.isMethod && m.isTerm).foreach{m =>
      m.annotations.foreach { a => 
        println(a.getClass)
        println(a.tpe)
        println(a.scalaArgs(0).getClass)
      }
    }
    val sym = universe.typeOf[T].members.filter(m => !m.isMethod && m.isTerm).last
    sym.asTerm
  }
}
