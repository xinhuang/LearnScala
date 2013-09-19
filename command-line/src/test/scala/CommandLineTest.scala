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

class NonStringOption {
  @Option("string")
  var string: String = ""
  @Option("int")
  var int: Int = 0
  @Option("double")
  var double: Double = 0
}

class FlagOption {
  @Option("myflag")
  var flag: Boolean = false
}

class CommandLineTest extends FlatSpec with ShouldMatchers {

  it should "parse option with 1 field via full name " in {
  	val args = Seq("--filename", "a.txt")
  	
  	val actual = CommandLine.parse[FullNameOption1](args)

  	actual.file should be ("a.txt")
  	actual.shallNotChange should be (7733)
  }

  it should "parse option with 2 fields via full name" in {
  	val args = Seq("--filename", "a.txt", "--version", "Apollo")
  	
  	val actual = CommandLine.parse[FullNameOption2](args)

  	actual.file should be ("a.txt")
  	actual.version should be ("Apollo")
  }

  it should "parse option with int field via full name" in {
    val args = Seq("--int", "33")
    
    val actual = CommandLine.parse[NonStringOption](args)

    actual.int should be (33)
  }

  it should "parse option with double field via full name" in {
    val args = Seq("--double", "44.55")
    
    val actual = CommandLine.parse[NonStringOption](args)

    actual.double should be (44.55)
  }

  it should "parse flag into boolean value" in {
    val args = Seq("--myflag")

    val actual = CommandLine.parse[FlagOption](args)

    actual.flag should be (true)
  }

}
