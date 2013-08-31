package learnscala.lengthcalculator

class Meter(value: Int) {
  val value_ = value

  override def equals(o: Any) = o match {
    case that: Meter =>  that.value_ == this.value_; 
    case _ => false
  }

  override def hashCode = value_.hashCode
}

