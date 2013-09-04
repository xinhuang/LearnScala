package learnscala.activeobject

import scala.language.reflectiveCalls

object RAII {
  def using[T <: { def dispose() }]
      (resource: T)
      (action: T => Unit) {
    try {
      action(resource)
    } finally {
      resource.dispose()
    }
  }
}

