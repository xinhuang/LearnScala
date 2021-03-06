package learnscala.commandline.test

import learnscala.commandline._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

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

  it should "parse option via abbr" in {
    val args = Seq("-f", "a.txt")

    val actual = CommandLine.parse[AbbrOption](args)

    actual.file should be ("a.txt")
  }

}
