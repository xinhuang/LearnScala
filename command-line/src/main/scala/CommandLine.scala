package learnscala.commandline

import scala.reflect.runtime.universe
import scala.reflect.Manifest

object CommandLine {
  private val rm = universe.runtimeMirror(getClass.getClassLoader)

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val option = m.runtimeClass.newInstance.asInstanceOf[T]

    val name = args(0).drop(2)
    val value = args(1)

    val im = rm.reflect(option)
    setField[T](im, name, value)

  	option
  }

  private def setField[T](im: universe.InstanceMirror, name: String, value: String)(implicit m: Manifest[T]) {
    val field = universe.typeOf[T].declaration(universe.newTermName(name)).asTerm.accessed.asTerm
    val fm = im.reflectField(field)
    fm.set(value)
  }
}
