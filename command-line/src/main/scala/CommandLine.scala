package learnscala.commandline

import scala.reflect.runtime.universe
import scala.reflect.Manifest
import scala.reflect.api.Mirror
import scala.reflect.api.Mirrors
import scala.annotation.StaticAnnotation

object CommandLine {
  val ru = reflect.runtime.universe
  val rm = ru.runtimeMirror(getClass.getClassLoader)

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val option = m.runtimeClass.newInstance.asInstanceOf[T]

    val name = args(0).drop(2)
    val value = args(1)

    setField[T](option, name, value)

  	option
  }

  private def setField[T](instance: T, name: String, value: String)(implicit m: Manifest[T]) {
    val im = rm.reflect(instance)
    val field = ru.typeOf[T].declaration(ru.newTermName(name)).asTerm.accessed.asTerm
    val fm = im.reflectField(field)
    fm.set(value)
  }
}
