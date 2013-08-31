package com.example.test

import org.scalatest.FunSuite
import com.example.Widget

class WidgetTest extends FunSuite {

  test("color") {
    expect("Blue") { new Widget().color }
  }

  test("disposition") {
    expect("Awesome") { new Widget().disposition }
  }
}
