package learnscala.lengthcalculator

class Meter(value: Double) {
  val value_ = value

  class WithAlmostEquals(value: Double) {
    def ~==(other: Double):Boolean = (value - other).abs <= Length.precision
  }

  implicit def add_~==(d: Double) = new WithAlmostEquals(d)

  override def equals(o: Any) = o match {
    case that: Meter =>  that.value_ ~== this.value_ 
    case _ => false
  }

  def <(o: Meter):Boolean = {
    if (this.value_ ~== o.value_)
      return false
    this.value_ < o.value_
  }

  def >(o: Meter):Boolean = {
    if (this.value_ ~== o.value_)
      return false
    o < this
  }

  override def hashCode = value_.hashCode
  override def toString = value_.toString + "m"
}


