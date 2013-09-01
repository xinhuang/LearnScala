package learnscala.lengthcalculator

object Length {
  val precision = 0.00001

  class Number2Meter(val value: Double) {
    def meter: Meter = {
      new Meter(value) 
    }
    def m = meter

    def centimeter: Meter = {
      new Meter(value / 100)
    }
    def cm = centimeter

    def decimeter: Meter = {
      new Meter(value / 10)
    }

    def inch: Meter = {
      new Meter(value * 0.0254)
    }
  }

  implicit def intExt(value: Int) = new Number2Meter(value)
  implicit def doubleExt(value: Double) = new Number2Meter(value)
}

