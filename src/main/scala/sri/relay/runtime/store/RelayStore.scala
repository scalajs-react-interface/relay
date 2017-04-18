package sri.relay.runtime.store

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scalajs.js.{UndefOr => U, undefined}
@js.native
@JSImport("relay-runtime", "Store")
class RelayStore(val source: MutableRecordSource) extends js.Object {}

@js.native
trait Observer[T] extends js.Object {
  val onCompleted: U[js.Function0[Unit]] = js.native
  val onError: U[js.Function1[js.Error, Unit]] = js.native
  val onNext: U[T => Unit] = js.native
}
