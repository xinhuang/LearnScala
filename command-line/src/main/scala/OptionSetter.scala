package learnscala.commandline

import scala.reflect.runtime.universe
import scala.reflect.Manifest
import scala.reflect.api.Annotations

class OptionSetter[T](implicit m: Manifest[T]) {
  val option: T = m.runtimeClass.newInstance.asInstanceOf[T]

  private val rm = universe.runtimeMirror(getClass.getClassLoader)
  private val im = rm.reflect(option)
  private val annotationType = universe.typeOf[Option]

  def setOption(name: String, value: String): Unit = {
    val field = findField(name)
    setField(field, value)
  }

  private def setField(field: universe.TermSymbol, value: String): Unit = {
    val fieldType = field.typeSignature
    val fm = im.reflectField(field)
    if (fieldType =:= universe.typeOf[String]) {
      fm.set(value)
    } else if (fieldType =:= universe.typeOf[Int]) {
      fm.set(value.toInt)
    } else if (fieldType =:= universe.typeOf[Double]) {
      fm.set(value.toDouble)
    } else if (fieldType =:= universe.typeOf[Boolean]) {
      fm.set(value.toBoolean)
    } else {
      throw new Exception("field " + field + " cannot be assigned a value of String")
    }
  }

  private def findField(optionName: String): universe.TermSymbol = {
    universe.typeOf[T].members.filter(m => !m.isMethod && m.isTerm)
      .foreach{ m =>
        m.annotations.find(a => a.tpe == annotationType) match {
          case Some(a) => {
            getOption(a) match {
              case Option(name, abbr) if name == optionName =>
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
    val classSymbol = annotationType.typeSymbol.asClass
    val classMirror = rm.reflectClass(classSymbol)
    val ctorTerm = annotationType.declaration(universe.nme.CONSTRUCTOR).asTerm
    if (!ctorTerm.isOverloaded) {
      val ctorSymbol = ctorTerm.asMethod
      val ctorMirror = classMirror.reflectConstructor(ctorSymbol)
      val instance = ctorMirror(args: _*).asInstanceOf[Option]
      return instance
    } else {
      val ctorSymbols = ctorTerm.alternatives
      ctorSymbols.foreach { symbol =>
        val ctorSymbol = symbol.asMethod
        if (ctorSymbol.paramss(0).length == args.length) {
          val ctorMirror = classMirror.reflectConstructor(ctorSymbol)
          val instance = ctorMirror(args: _*).asInstanceOf[Option]
          return instance
        }
      }
    }
    throw new Exception("Cannot find ctor")
  }
}
