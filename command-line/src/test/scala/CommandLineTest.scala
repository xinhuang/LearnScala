package learnscala.commandline.test

import learnscala.commandline._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class TestFileOption {
  @Option("filename")
  var file: String = ""
  private var privatefield = 1
}

class CommandLineTest extends FlatSpec with ShouldMatchers {

  "--file a.txt" should "be parsed to TestFileOption" in {
  	val args = Seq("--file", "a.txt")
  	
  	val actual = CommandLine.parse[TestFileOption](args)

  	actual.file should be ("a.txt")
  }
}
