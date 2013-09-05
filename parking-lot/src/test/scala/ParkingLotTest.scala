package learnscala.parkinglot.test

import learnscala.parkinglot._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ParkingLotTest extends FlatSpec with ShouldMatchers {
  val sut = new ParkingLot(3)

  "empty lot" should "have size equals 0" in {
  	sut.size should be (0)
  }

  "parking lot size" should "be 1 after 1 car parked" in {
  	sut park new Car

  	sut.size should be (1)
  }
}
