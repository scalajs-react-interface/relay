package sri.relay.runtime

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("relay-runtime", JSImport.Namespace)
object RelayRuntime extends js.Object {}

@js.native
trait Disposable extends js.Object {

  def dispose(): Unit = js.native
}
