package sri.relay.runtime.store

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{UndefOr => U}
@js.native
@JSImport("relay-runtime", "Store")
class RelayStore(val source: MutableRecordSource) extends js.Object {}

@js.native
trait Observer[T] extends js.Object {
  def onCompleted: U[js.Function0[Unit]] = js.native
  def onError: U[js.Function1[js.Error, Unit]] = js.native
  def onNext: U[js.Function1[T, Unit]] = js.native
}
