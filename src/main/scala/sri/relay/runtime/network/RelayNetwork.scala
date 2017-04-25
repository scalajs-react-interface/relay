package sri.relay.runtime.network

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("relay-runtime", "Network")
object RelayNetwork extends js.Object {

  def create(fetch: FetchFunction,
             subscribe: SubscribeFunction = ???): RelayNetwork =
    js.native
}

@js.native
trait RelayNetwork extends js.Object {
  val fetch: FetchFunction = js.native
  val request: RequestResponseFunction = js.native
  val requestStream: RequestStreamFunction = js.native
}
