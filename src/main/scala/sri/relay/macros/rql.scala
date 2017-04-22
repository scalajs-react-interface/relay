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
object rql {

  private final val qglSchemaPrefix = "gqlschema="
  private final val tempDirName = System.getProperty("java.io.tmpdir")
  private final val script =
    """
      // `babel-relay-plugin` returns a function for creating plugin instances
      var getBabelRelayPlugin = require('babel-plugin-relay');
      var babel = require('babel-core')

      // load previously saved schema data (see "Schema JSON" below)
//      var schemaData = require(process.argv[2]);

      // create a plugin instance
//      var plugin = getBabelRelayPlugin(schemaData.data);

      process.stdin.resume();
      process.stdin.setEncoding('utf8');
      var data = "";

      process.stdin.on('data', function(chunk) {
          data += chunk;
      });

      process.stdin.on('end', function() {
          var result = babel.transform(data, {
            plugins: [getBabelRelayPlugin]
          });
          process.stdout.write(result.code);
      });
    """

  def rqlMacroImpl(c: whitebox.Context)(
      args: c.Expr[js.Any]*): c.Expr[js.Any] = {
    import c.universe._

    val constantParts = extractConstantPartsAndTreatEscapes(c)

    val names = args.map(a => (a, c.freshName("arg")))
    val q =
      constantParts.zip(names.map(s => "${" + s._2 + "}") :+ "").foldLeft("") {
        case (a, (const, varN)) => a + const + varN
      }

    val result = for {
      schema <- c.settings
        .find(_.startsWith(qglSchemaPrefix))
        .map(s => Right(s.substring(qglSchemaPrefix.length)))
        .getOrElse(
          Left(
            s"Missing schema setting(s), add -Xmacro-settings=$qglSchemaPrefix<path-to-schema.json> " +
              "to scalacOptions."
          )
        )
        .right
      output <- runBabelRelay(q, schema).right
    } yield {
      // FIXME: This section is really hacky
      // Split the args from the function body and use our replacements with our replacements and use the direct ones.
      val Array(rql, _) = "\\}\\)\\(".r.split(output)
      // Get rid of the initial function prefix and pass in Relay, so we don't have to use
      // globals
      val cleanedRql = rql.substring("(function (".length)
      val sep = if (args.isEmpty) "" else ", "

      s"(function (Relay$sep" + cleanedRql + "})"

    }

    val output = result.fold(s => c.abort(c.enclosingPosition, s), identity)

    c.Expr[js.Any](q"""
        {
          import _root_.scala.scalajs.js
          val Relay = _root_.sri.relay.Relay

          val f: js.Function = js.eval($output).asInstanceOf[js.Function]
          f.call(null, Relay, ..${names.map(_._1)})
        }
     """)
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

  private def runBabelRelay(relayQuery: String,
                            schemaFilePath: String): Either[String, String] = {

    val nodeModulesPath = {
      val tempDir = new File(tempDirName, "relayscratch")
      if (tempDir.mkdir()) {
        //babel-relay-plugin@0.10.0 babel-core@6.9.0
        sys.process
          .Process(Seq("yarn",
                       "add",
                       "babel-plugin-relay@1.0.0-alpha.2",
                       "babel-core@6.9.0"),
                   tempDir)
          .!!
      }
      tempDir.toString
    }

    for {
      _ <- {
        if (!new File(schemaFilePath).exists())
          Left(s"Schema file $schemaFilePath, does not exist, aborting.")
        else Right(())
      }.right
      _ <- {
        if (!new File(nodeModulesPath).exists())
          Left(
            s"""The directory "${nodeModulesPath}", does not exist, aborting.""")
        else Right(())
      }.right
      r <- execBabelNode(relayQuery, schemaFilePath, nodeModulesPath).right
    } yield r
  }

  private def execBabelNode(
      relayQuery: String,
      schemaFilePath: String,
      nodeModulesPath: String): Either[String, String] = {
    import scala.sys.process._
    val buildFile = getOrCreateScript(tempDirName)
    val send = s"graphql`$relayQuery`"
    var output = ""
    var error = Option.empty[String]
    val io = new ProcessIO(
      { stdin =>
        stdin.write(send.getBytes)
        stdin.close()
      }, { stdout =>
        output = convertStreamToString(stdout)
        println(s"output : $output")
        stdout.close()
      }, { stderr =>
        val err = convertStreamToString(stderr)
        if (err.nonEmpty) {
          error = Some(err)
        }
        stderr.close()
      }
    )

    Process(
      Seq("node", buildFile, schemaFilePath),
      new File(nodeModulesPath),
      "NODE_PATH" -> Paths.get(nodeModulesPath, "node_modules").toString
    ).run(io).exitValue()

    error.map { err =>
      Left(s"\n--------Babel Output: $err")
    } getOrElse Right(output)
  }

  private def convertStreamToString(is: java.io.InputStream): String = {
    val s = new java.util.Scanner(is).useDelimiter("\\A")
    if (s.hasNext()) s.next()
    else ""
  }

  private def getOrCreateScript(tempDir: String): String = {
    val f = new File(tempDir, "relayql-stdin.js")
    if (f.exists())
      f.getAbsolutePath
    else {
      f.createNewFile()
      f.setWritable(true)
      val pw = new PrintWriter(f)
      pw.write(script)
      pw.close()
      f.getAbsolutePath
    }
  }

  //////////////////////

  implicit class RsqlStringContext(val sc: StringContext) extends AnyVal {
    def rql(args: js.Any*): js.Any = macro rqlMacroImpl
  }
}
