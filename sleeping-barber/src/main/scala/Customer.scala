package learnscala.sleepingbarber

class Customer(val id: Int) {
  var shorn_ = false

  def shorn = shorn_
  def shorn_=(value: Boolean) = {
  	shorn_ = value
  	if (value)
  	  println("[c] " + this + " got hair cut today")
  	else
  	  println("[c] " + this + " feels weird because hair grows again!")
  }

  override def toString =  "customer " + id
  
}
