package learnscala.lengthcalculator.test

import learnscala.lengthcalculator.Length._

import org.scalatest.FunSuite

class LengthCalculatorTest extends FunSuite {

  test("1m == 1m") {
    assert(1.meter === 1.meter)
  }

  test("100cm == 1m") {
    assert(100.centimeter === 1.meter)
  }

  test("10cm == 0.1m") {
    assert(10.centimeter === 0.1.meter)
  }

  test("10cm == 1dm") {
    assert(10.centimeter === 1.decimeter)
  }

  test("10.inch == 25.4.cm") {
    assert(10.inch === 25.4.cm)
  }

  test("1.cm < 2.cm") {
    assert(1.cm < 2.cm)
  }

  test("10.inch < 1.m") {
    assert(10.inch < 1.m)
  }
  
  test("1.m > 1.inch") {
    assert(1.m > 1.inch)
  }
}

