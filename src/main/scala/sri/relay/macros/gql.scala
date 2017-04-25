package sri.relay.macros

import java.io.File
import java.io.PrintWriter
import java.nio.file.Paths

import scala.reflect.macros.whitebox
import scala.scalajs.js

/**
  * https://gist.github.com/dispalt/578486191c2a023a420da949bfa423d5
  * Had some serious help from
  * https://github.com/dkomanov/scala-string-format/blob/master/scala-string-format/src/main/scala/com/komanov/stringformat/macros/MacroConcat.scala
  */
object gql {

  private final val qglSchemaPrefix = "gqlschema="
  private final val tempDirName = System.getProperty("user.dir")

  def rqlMacroImpl(c: whitebox.Context)(
      args: c.Expr[js.Any]*): c.Expr[js.Function0[js.Any]] = {
    import c.universe._

    val constantParts = extractConstantPartsAndTreatEscapes(c)

    val names = args.map(a => (a, c.freshName("arg")))
    val q =
      constantParts.zip(names.map(s => "${" + s._2 + "}") :+ "").foldLeft("") {
        case (a, (const, varN)) => a + const + varN
      }

    println(s"query : $q")

    val (sourceFileName, outputFileName) = saveQueryAndGetFilePath(q)

    val content =
      s"""
         |graphql`${q}`
       """.stripMargin

    val generatedFilePath =
      s"./queries/__generated__/${outputFileName}.graphql.js"

    writeToFile(s"./assets/queries/$sourceFileName.js", content)

    c.Expr[js.Function0[js.Any]](q"""
        {
           import scala.scalajs.js
                      import scala.scalajs.js.annotation.JSImport

            val func:js.Function0[js.Any] = () => js.Dynamic.global.require($generatedFilePath)
            func
        }
     """)
  }

  def saveQueryAndGetFilePath(input: String): (String, String) = {
    val x = input.trim.split(" ")
    val op = x.head.trim
    val queryWithoutOp = x.tail.mkString
    var sourceFileName = ""
    var outputFileName = ""
    if (op == "query") {
      outputFileName = queryWithoutOp
        .split('{')
        .head
        .trim
      sourceFileName = outputFileName.replace("Query", "")
    } else if (op == "mutation") {
      outputFileName = queryWithoutOp
        .split('(')
        .head
        .trim
      sourceFileName = outputFileName.replace("Mutation", "")
    } else if (op == "fragment") {
      outputFileName = queryWithoutOp
        .split("on")
        .head
        .trim
      sourceFileName = outputFileName.split("_").head.trim
    }
    (sourceFileName, outputFileName)
  }

  def writeToFile(path: String, content: String) = {
    val pw = new PrintWriter(new File(path))
    try pw.write(content)
    finally pw.close()
  }
  private def extractConstantPartsAndTreatEscapes(
      c: whitebox.Context): List[String] = {
    import c.universe._

    val rawParts = c.prefix.tree match {
      case Apply(_, List(Apply(_, list))) => list
    }

    rawParts.map {
      case Literal(Constant(rawPart: String)) =>
        StringContext.treatEscapes(rawPart)
    }
  }

  //////////////////////

  implicit class RsqlStringContext(val sc: StringContext) extends AnyVal {
    def gql(args: js.Any*): js.Function0[js.Any] = macro rqlMacroImpl
  }
}
