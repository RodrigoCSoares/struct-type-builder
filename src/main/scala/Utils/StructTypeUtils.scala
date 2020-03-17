package Utils

import java.security.InvalidParameterException

object StructTypeUtils {

  implicit class StructTypeBuilder(schemaLines: List[String]) {
    def buildStructTypeText: String = {
      val schemaWithoutRootLine = removeRootLine(schemaLines)
      val structTypeString = schemaWithoutRootLine.map(buildStructTypeTextLine)
      addStructTypeConstructor(structTypeString).reduce((acc, str) => {
        s"$acc \n $str"
      })
    }
  }

  private def buildStructTypeTextLine(line: String): String = {
    val lineWithoutIndentationNotation = removeIndentationNotation(line)
    val fieldName = getFieldName(lineWithoutIndentationNotation)
    val fieldStructType = toStructType(getFieldType(lineWithoutIndentationNotation))

    s""" StructField("$fieldName", $fieldStructType, nullable = true),"""
  }

  private def removeIndentationNotation(schemaLine: String): String = schemaLine.drop(5)

  private def getFieldName(schemaLine: String): String = {
    val pattern = "\\w+:".r

    pattern.findFirstIn(schemaLine) match {
      case Some(value) => value.dropRight(1)
      case None => throw new InvalidParameterException("Field not found")
    }
  }

  private def getFieldType(schemaLine: String): String = {
    val pattern = ": \\w+".r

    pattern.findFirstIn(schemaLine) match {
      case Some(value) => value.drop(2)
      case None => throw new InvalidParameterException("Field not found")
    }
  }

  private def addStructTypeConstructor(structTypeLines: List[String]): List[String] = {
    val constructor = List("StructType(", "Array(")
    val tail = List(")", ")")

    constructor ++ structTypeLines ++ tail
  }

  private def removeRootLine(schemaLines: List[String]): List[String] = {
    schemaLines.tail
  }

  private def toStructType(schemaType: String) = {
    schemaType match {
      case "byte" => "ByteType"
      case "short" => "ShortType"
      case "integer" => "IntegerType"
      case "long" => "LongType"
      case "float" => "FloatType"
      case "double" => "DoubleType"
      case "decimal" => "DecimalType"
      case "string" => "StringType"
      case "binary" => "BinaryType"
      case "boolean" => "BooleanType"
      case "date" => "DateType"
      case "timestamp" => "TimestampType"
      case "array" => "ArrayType"
      case "map" => "MapType"
      case "struct" => "StructType"
    }
  }
}
