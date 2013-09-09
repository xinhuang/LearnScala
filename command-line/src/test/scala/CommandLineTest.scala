package learnscala.commandline.test

import learnscala.commandline._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CommandLineTest extends FlatSpec with ShouldMatchers {
  "--file a.txt" should "be parsed as args.file = a.txt" in {
  	val args = Seq("--file", "a.txt")

  	val sut = CommandLine.parse(args)

  	sut.file should be ("a.txt")
  }
}
