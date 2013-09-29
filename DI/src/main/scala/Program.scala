package learnscala.di

object Program {
  def main(args: Array[String]): Unit = {

    println("Cake Pattern:")
    val warmer = cakepattern.ComponentRegistry.warmer
    warmer.trigger

    println("Structural Typing:")
    new structuraltyping.Client(structuraltyping.Config)
  }
}
