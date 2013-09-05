package learnscala.lengthcalculator

import scala.language.implicitConversions

object Length {
  implicit def intExt(value: Int) = new Number2Meter(value)
  implicit def doubleExt(value: Double) = new Number2Meter(value)

  class Number2Meter(val value: Double) {
    import collection.mutable.Map
    private val units:Map[String, Double] = Map(
      "meter"       -> 1,
      "decimeter"   -> 0.1,
      "centimeter"  -> 0.01,
      "inch"        -> 0.0254
    )
    private def create(name:String):Meter = {
      new Meter(value * units(name))
    }

    def meter: Meter = create("meter")
    def m = meter

    def centimeter = create("centimeter")
    def cm = centimeter

    def decimeter: Meter = create("decimeter")

    def inch: Meter = create("inch")
  }

  val precision = 0.00001.m
}

