package learnscala.lengthcalculator

object Program {
  def main(args: Array[String]) = {
    println("1m == 1m: " + (new Meter(1) == new Meter(1)))
    println("1m == 2m: " + (new Meter(1) == new Meter(2)))
  }
}
