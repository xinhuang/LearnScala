package learnscala.commandline

import scala.reflect.runtime.universe
import scala.reflect.Manifest
import scala.reflect.api.Annotations
import scala.annotation.StaticAnnotation

case class Option(val longname: String) extends StaticAnnotation

object CommandLine {
  private val rm = universe.runtimeMirror(getClass.getClassLoader)

  def parse[T](args: Seq[String])(implicit m: Manifest[T]): T = {
    val option = m.runtimeClass.newInstance.asInstanceOf[T]

    for (i <- 1 to args.length / 2) {
      val optionName = args(i * 2 - 2).drop(2)
      val value = args(i * 2 - 1)

      val field = findField[T](optionName)
      val im = rm.reflect(option)
      setField(im, field, value)
    }

  	option
  }

  private def setField(im: universe.InstanceMirror, field: universe.TermSymbol, value: String) {
    val fm = im.reflectField(field)
    fm.set(value)
  }

  private def findField[T](optionName: String)(implicit m: Manifest[T]): universe.TermSymbol = {
    universe.typeOf[T].members.filter(m => !m.isMethod && m.isTerm)
      .foreach{ m =>
        val annotationType = universe.typeOf[Option]
        m.annotations.find(a => a.tpe == annotationType) match {
          case Some(a) => {
            getOption(a) match {
              case Option(currentName) if currentName == optionName =>
                  return m.asTerm
              case _ =>
            }
          }
          case None => 
        }
      }
    throw new Exception("No option found matching " + optionName)
  }

  private def getOption(a: universe.AnnotationApi): Option = {
    val args = a.scalaArgs.map(a => a.productElement(0).asInstanceOf[universe.Constant].value)
    val classType = universe.typeOf[Option]
    val classSymbol = classType.typeSymbol.asClass
    val classMirror = rm.reflectClass(classSymbol)
    val ctorSymbol = classType.declaration(universe.nme.CONSTRUCTOR).asMethod
    val ctorMirror = classMirror.reflectConstructor(ctorSymbol)
    val instance = ctorMirror(args: _*).asInstanceOf[Option]
    return instance
  }
}
