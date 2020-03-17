import Utils.StructTypeUtils._

import scala.io.Source

object Main extends App {
  val schemaLines = readTextFileLines(args(0))
  printf(schemaLines.buildStructTypeText)


  def readTextFileLines(str: String): List[String] = {
    val bufferedSource = Source.fromFile(args(0))
    val fileLines = bufferedSource.getLines().toList
    bufferedSource.close()
    fileLines
  }
}
