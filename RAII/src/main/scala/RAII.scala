package learnscala.raii

import scala.language.reflectiveCalls

object RAII {
  def using[T <: { def dispose() }] (resource: T) (action: T => Unit) {
  	try {
  	  action(resource)
  	} finally {
  	  resource.dispose()
  	}
  }

  def using[T <: IDisposable] (disposable: T) (action: IDisposable => Unit) {
  	try {
  	  action(disposable)
  	} finally {
  	  disposable.dispose()
  	}
  }
}
