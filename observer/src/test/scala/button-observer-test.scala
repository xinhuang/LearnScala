package learnscala.observer.test

import learnscala.observer._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ButtonObserverTest extends FlatSpec with ShouldMatchers {
  "An observer counting button clicks" should "see all clicks" in {
	val button1 = new ButtonSubjectObserver.ObservableButton("button1")
	val button2 = new ButtonSubjectObserver.ObservableButton("button2")
	val button3 = new ButtonSubjectObserver.ObservableButton("button3")
	val buttonObserver = new ButtonClickObserver

	button1.addObserver(buttonObserver)
	button2.addObserver(buttonObserver)
	button3.addObserver(buttonObserver)

	clickButton(button1, 1)
	clickButton(button2, 2)
	clickButton(button3, 3)

	buttonObserver.clicks("button1") should be (1)
	buttonObserver.clicks("button2") should be (2)
	buttonObserver.clicks("button3") should be (3)
  }

  def clickButton(button: Button, times: Int) = {
  	(1 to times).foreach(_ => button.click())
  }
}