package learnscala.lengthcalculator.test

import learnscala.lengthcalculator._

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class LengthCalculatorTest extends FlatSpec with ShouldMatchers {

  "Meter instances with same value" should "be equal" in {
    new Meter(1) should equal (new Meter(1))
  }

}

