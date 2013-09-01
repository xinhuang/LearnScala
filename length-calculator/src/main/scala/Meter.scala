package learnscala.lengthcalculator

class Meter(value: Double) {
  val value_ = value

  import Length._
  override def equals(o: Any) = o match {
    case that: Meter => compareTo(that) == 0
    case _ => false
  }

  def *(scale: Double):Meter = new Meter(value * scale)

  def <(o: Meter):Boolean = compareTo(o) < 0
  def >(o: Meter):Boolean = compareTo(o) > 0

  def compareTo(rhs: Meter):Int = {
    if ((rhs.value_ - this.value_).abs < Length.precision.value_)
      return 0
    if (this.value_ > rhs.value_) 1 else -1
  }

  override def hashCode = value_.hashCode
  override def toString = value_.toString + "m"
}


