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

      @Option("AFlag")
      var flag: Boolean = false
    }

    val args = Seq["--stringValue", "a string", "--intValue", "42", "--ADoubleValue", "0.42", "--AFlag"]

    val option: MyOption = CommandLine.parse[MyOption](args)

    option.stringValue should be ("a string")
    option.intValue should be (42)
    option.doubleValue should be (0.42)
    option.flag should be (true)

## TODO

* abbreviation
* set a option to be required 
* option help message
* default value
* verb option
* getopt style option
* list values for a option

## License

For sure the [WTFPL](http://www.wtfpl.net/about/).

[commandlineurl]: https://github.com/gsscoder/commandline
