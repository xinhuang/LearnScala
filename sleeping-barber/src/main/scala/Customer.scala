package learnscala.sleepingbarber

class Customer(val id: Int) {
  var shorn: Boolean = false

  override def toString =  "customer " + id 
  
}
