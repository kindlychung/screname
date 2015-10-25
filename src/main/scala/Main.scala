import scala.collection.mutable.Map


object Main extends App {
  val usage =
    """
      |A commandline tool for renaming files (written in Scala)
      |
      |Usage:
      |
      |  java -jar screname-assembly-1.0.jar [-a] [-t] -s search_pattern -r replace_pattern filenames
      |
      |Type `java -jar screname-assembly-1.0.jar` and return to print this message.
    """.stripMargin

  // Print usage and exit
  def printHelpExit() = {
    println(usage)
    System.exit(0)
  }

  if (args.length == 0) {
    printHelpExit()
  }
  val arglist = args.toList
  type OptionMap = Map[Symbol, Any]

  // Is the arg a switch?
  def isSwitch(s: String) = (s(0) == '-')

  // Function for parse args
  def nextOption(map: OptionMap, list: List[String]): OptionMap = {
    list match {
      // List is exhausted
      case Nil => map
      case "-a" :: tail => {
        nextOption(map ++ Map('replaceAll -> true), tail)
      }
      case "-t" :: tail => {
        nextOption(map ++ Map('testRun -> true), tail)
      }
      case "-s" :: searchPattern :: tail => {
        if (map.contains('searchPattern)) throw new IllegalArgumentException("Only one search pattern allowed.")
        nextOption(map ++ Map('searchPattern -> searchPattern), tail)
      }
      case "-r" :: replacePattern :: tail => {
        if (map.contains('replacePattern)) throw new IllegalArgumentException("Only one replace pattern allowed")
        nextOption(map ++ Map('replacePattern -> replacePattern), tail)
      }
      case filename :: tail => {
        map.update('files, filename :: (map.getOrElse('files, List())).asInstanceOf[List[String]])
        nextOption(map, tail)
      }
    }
  }

  val options = nextOption(Map(), arglist)
  // Validate options
  if (!options.contains('searchPattern)) throw new IllegalArgumentException("No search pattern provided")
  if (!options.contains('replacePattern)) throw new IllegalArgumentException("No replace pattern provided")
  if (options.getOrElse('files, List()).asInstanceOf[List[String]].size == 0) throw new IllegalArgumentException("No filenames provided")

  // Rename
  val fileRenamer = new FileRenameByPattern(options.getOrElse('files, List()).asInstanceOf[List[String]], options.getOrElse('searchPattern, "").asInstanceOf[String], options.getOrElse('replacePattern, "").asInstanceOf[String], options.getOrElse('replaceAll, false).asInstanceOf[Boolean])
  if (options.getOrElse('testRun, false).asInstanceOf[Boolean]) {
    fileRenamer.printFileNamePairs()
  } else {
    fileRenamer.rename();
  }
}





