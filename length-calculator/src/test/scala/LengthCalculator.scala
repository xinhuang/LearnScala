package learnscala.lengthcalculator.test

import learnscala.lengthcalculator._

import org.scalatest.FunSuite

class LengthCalculatorTest extends FunSuite {

  test("Meter instances with same value should be equal") {
    assert(new Meter(1) == new Meter(1))
  }

  test("Meter instances with different value should not be equal") {
    assert(new Meter(2) != new Meter(1))
  }

}

