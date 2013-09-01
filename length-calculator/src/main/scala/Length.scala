package learnscala.lengthcalculator

object Length {
  val precision = 0.00001

  implicit def intExt(value: Int) = new Number2Meter(value)
  implicit def doubleExt(value: Double) = new Number2Meter(value)

  class Number2Meter(val value: Double) {
    def meter: Meter = new Meter(value)
    def m = meter

    def centimeter = 0.01.m * value
    def cm = centimeter

    def decimeter: Meter = 0.1.m * value

    def inch: Meter = 0.0254.m * value
  }

}

