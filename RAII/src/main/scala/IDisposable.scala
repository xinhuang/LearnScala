package learnscala.raii

trait IDisposable {
  def dispose(): Unit
}