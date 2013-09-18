# Command Line 

This is a command line parser for Scala. The idea is inspired by [command-line][commandlineurl] developed in C#.

## Example

    class MyOption {
      @Option("stringValue")
      var stringValue: String = ""
      @Option("intValue")
      var intValue: Int = 0
      @Option("ADoubleValue")
      var doubleValue: Double = 0
    }

    val args = Seq["--stringValue", "a string", "--intValue", "42", "--ADoubleValue", "0.42"]

    val option: MyOption = CommandLine.parse[MyOption](args)

    println("stringValue = " + option.stringValue)
    println("intValue = " + option.intValue)
    println("doubleValue = " + option.doubleValue)

## TODO

* abbreviation
* set a option to be required 
* option help message
* default value
* verb option
* getopt style option

## License

For sure the [WTFPL](http://www.wtfpl.net/about/).

[commandlineurl]: https://github.com/gsscoder/commandline
