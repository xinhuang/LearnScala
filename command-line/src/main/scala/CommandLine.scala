package learnscala.commandline

object CommandLine {
  def parse(args: Seq[String]): CmdArgs = {
  	var result = new CmdArgs()

  	args.groupBy(optionSpec).foreach {
  	  option: (String, Seq[String]) =>
  	  result.updateDynamic(option._1)(option._2(1))
  	}

  	result
  }

  var lastOption = ""
  def optionSpec(arg: String): String = {
  	val delim = "--"
  	if (arg startsWith delim) {
  	  lastOption = arg.drop(2)
  	}
  	lastOption
  }
}
