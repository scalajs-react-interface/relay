package sri.relay.route

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("RelayMetaRoute", JSImport.Namespace)
object RelayMetaRoute extends js.Object {
  def get(name: String): RelayMetaRoute = js.native
}

/**
  * Meta route based on the real route; provides access to the route name in
  * queries.
  */
@js.native
@JSImport("RelayMetaRoute", JSImport.Default)
class RelayMetaRoute(name: String) extends js.Object
