package learnscala.parkinglot

class ParkingLot(capacity: Int) {
  var size = 0

  def park(car: Car): Unit = {
  	size += 1
  }
}
