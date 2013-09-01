package learnscala.lengthcalculator

import learnscala.lengthcalculator.Length._

object Program {
  def main(args: Array[String]) = {
    println("1m == 1m: " + (1.meter == 1.meter))
    println("2m == 1m: " + (2.meter == 1.meter))
  }
}
