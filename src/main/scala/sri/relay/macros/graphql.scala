package sri.relay.macros

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

import scala.io.Source
import scala.reflect.macros.whitebox
import scala.scalajs.js
import scala.sys.process.Process

object graphql {

  def gqlMacroImpl(c: whitebox.Context)(
      args: c.Expr[js.Any]*): c.Expr[js.Function0[js.Any]] = {
    import c.universe._

    val constantParts = extractConstantPartsAndTreatEscapes(c)

    val names = args.map(a => (a, c.freshName("arg")))
    val q =
      constantParts.zip(names.map(s => "${" + s._2 + "}") :+ "").foldLeft("") {
        case (a, (const, varN)) => a + const + varN
      }

    val (sourceFileName, outputFileName) = getFileSourceDestinationFileNames(q)

    if (sourceFileName.isEmpty || outputFileName.isEmpty)
      c.abort(
        c.enclosingPosition,
        "you must provide query/mutation/fragment/subscription in graphql")

    val content =
      s"""
         |graphql`${q}`
       """.stripMargin

    val generatedFilePath =
      s"../../queries/__generated__/${outputFileName}.graphql.js"

    val sourceFilePath = s"./queries/$sourceFileName.js"

    if (Files.exists(Paths.get(sourceFilePath)) && Source
          .fromFile(sourceFilePath)
          .getLines()
          .mkString("\n") == content) {
      // if content didn't changed do nothing

    } else {
      writeToFile(sourceFilePath, content)
      val result = scala.util
        .Try(Process(
          s"relay-compiler --src ./queries/ --schema ./data/schema.graphql").!!)
        .toOption
      if (result.isEmpty) {
        new File(sourceFilePath).delete()
        c.abort(c.enclosingPosition,
                "Relay Compiler failed with Error check your graphql")
      }
    }

    c.Expr[js.Function0[js.Any]](q"""
          import scala.scalajs.js
          val func:js.Function0[js.Any] = () => sri.universal.AssetLoader.require[js.Dynamic]($generatedFilePath)
          func
     """)
  }

  def getFileSourceDestinationFileNames(input: String): (String, String) = {
    val x = input.trim.split(" ")
    val op = x.head.trim
    val queryWithoutOp = x.tail.mkString(" ")
    var sourceFileName = ""
    var outputFileName = ""

    if (op == "fragment") {
      outputFileName = queryWithoutOp
        .split(" on ")
        .head
        .trim
      sourceFileName = outputFileName
    } else {
      val paramsIndex = queryWithoutOp.indexOf('(')
      val bracesIndex = queryWithoutOp.indexOf('{')
      val separator =
        if (paramsIndex > 0 && paramsIndex < bracesIndex) '(' else '{'

      if (op == "query") {
        outputFileName = queryWithoutOp
          .split(separator)
          .head
          .trim
        sourceFileName = outputFileName.replace("Query", "")
      } else if (op == "mutation") {
        outputFileName = queryWithoutOp
          .split(separator)
          .head
          .trim
        sourceFileName = outputFileName.replace("Mutation", "")
      } else if (op == "subscription") {
        outputFileName = queryWithoutOp
          .split(separator)
          .head
          .trim
        sourceFileName = outputFileName.replace("Subscription", "")
      }
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
    def graphql(args: js.Any*): js.Function0[js.Any] = macro gqlMacroImpl
  }
}
