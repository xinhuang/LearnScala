package learnscala.raii

import scala.language.reflectiveCalls

object using {
  def apply[T <: { def dispose() }, R] (resource: T) (action: T => R): R = {
  	withResource(resource)(action)(r => r.dispose())
  }

  def withResource[T, R] (resource: T)(action: T => R)(release: T => Unit): R = {
  	try {
  	  action(resource)
  	} finally {
  	  release(resource)
  	}
  }
}
