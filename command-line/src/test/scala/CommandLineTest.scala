package learnscala.commandline.test

import learnscala.commandline._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class FullNameOption1 {
  @Option("filename")
  var file: String = ""
  var shallNotChange = 7733
}

class FullNameOption2 {
  @Option("filename")
  var file: String = ""
  @Option("version")
  var version: String = ""
}

class CommandLineTest extends FlatSpec with ShouldMatchers {

  // it should "parse option with 1 field via full name " in {
  // 	val args = Seq("--filename", "a.txt")
  	
  // 	val actual = CommandLine.parse[FullNameOption1](args)

  // 	actual.file should be ("a.txt")
  // 	actual.shallNotChange should be (7733)
  // }

  it should "parse option with 2 fields via full name" in {
  	val args = Seq("--filename", "a.txt", "--version", "Apollo")
  	
  	val actual = CommandLine.parse[FullNameOption2](args)

  	actual.file should be ("a.txt")
  	actual.version should be ("Apollo")
  }

}
