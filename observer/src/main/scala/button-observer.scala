package learnscala.observer

object ButtonSubjectObserver extends SubjectObserver {
  type S = ObservableButton
  type O = ButtonObserver

  class ObservableButton(name: String) extends Button(name) with Subject {
  	override def click() = {
  	  super.click()
  	  notifyObservers
  	}
  }

  trait ButtonObserver extends Observer {
  	def receiveUpdate(button: ObservableButton)
  }
}