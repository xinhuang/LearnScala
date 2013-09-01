package learnscala.lengthcalculator

class Meter(value: Double) {
  val value_ = value

  override def equals(o: Any) = o match {
    case that: Meter =>  that.value_ == this.value_; 
    case _ => false
  }

  override def hashCode = value_.hashCode
}

object Meter {

  class Number2Meter(val value: Double) {
    def meter: Meter = {
      new Meter(value) 
    }
    def centimeter: Meter = {
      new Meter(value / 100)
    }
  }

  implicit def intExt(value: Int) = new Number2Meter(value)
  implicit def intExt(value: Double) = new Number2Meter(value)
}

