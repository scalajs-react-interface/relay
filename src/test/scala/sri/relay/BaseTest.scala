package sri.relay;

import org.scalactic.source.Position
import org.scalatest._

import scala.scalajs.js.JavaScriptException

class BaseTest extends FunSuite with BeforeAndAfter{

  override protected def test(testName: String, testTags: org.scalatest.Tag*)(testFun: => Any)(implicit pos: Position) = {
    super.test(testName, testTags: _*)(try testFun catch {
      case jse @ JavaScriptException(e) =>
        println(e)
        jse.printStackTrace()
        throw jse
    })
  }

}
