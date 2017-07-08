package sri.relay.runtime

import org.scalajs.dom.raw.{Blob, File}
import sri.macros.{FunctionObjectMacro, OptDefault, OptionalParam}
import sri.relay.runtime.store.{MutableRecordSource, Observer}

import scala.scalajs.js
import scala.scalajs.js.{Promise, |, UndefOr => U}

package object network {

  type FetchFunction = js.Function4[ConcreteBatch /*operation*/,
                                    js.Object /*variables*/,
                                    CacheConfig /*cacheConfig*/,
                                    js.Dictionary[File | Blob] /*uploadables*/,
                                    js.Thenable[QueryPayload]]

  type RequestResponseFunction =
    js.Function4[ConcreteBatch /*operation*/,
                 js.Object /*variables*/,
                 CacheConfig /*cacheConfig*/,
                 js.Dictionary[File | Blob] /*uploadables*/,
                 js.Thenable[RelayResponsePayload]]

  type RequestStreamFunction =
    js.Function4[ConcreteBatch /*operation*/,
                 js.Object /*variables*/,
                 CacheConfig /*cacheConfig*/,
                 Observer[RelayResponsePayload] /*observer*/,
                 Disposable]

  type SubscribeFunction =
    js.Function4[ConcreteBatch /*operation*/,
                 js.Object /*variables*/,
                 CacheConfig /*cacheConfig*/,
                 Observer[QueryPayload] /*observer*/,
                 Disposable]
}

@js.native
trait ConcreteFragment extends js.Object

@js.native
trait ConcreteBatch extends js.Object {
  val kind: String = js.native
  val fragment: ConcreteFragment = js.native
  val id: js.UndefOr[String] = js.native
  val metadata: js.Dynamic = js.native
  val name: String = js.native
  val query: ConcreteRoot = js.native
  val text: String = js.native
}

@js.native
trait ConcreteRoot extends js.Object

trait CacheConfig extends js.Object

object CacheConfig {

  def apply(force: OptionalParam[Boolean] = OptDefault,
            poll: OptionalParam[Int] = OptDefault): CacheConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[CacheConfig]
  }
}

@js.native
trait PayloadError extends js.Object {
  val message: String = js.native
  val locations: js.UndefOr[js.Array[js.Dynamic]] = js.native
}

@js.native
trait QueryPayload extends js.Object {
  val data: js.UndefOr[js.Dynamic] = js.native
  val errors: js.UndefOr[js.Array[PayloadError]] = js.native
}

@js.native
trait RelayResponsePayload extends js.Object {
  val fieldPayloads: U[js.Array[HandleFieldPayload]] = js.native
  val source: MutableRecordSource = js.native
  val errors: js.UndefOr[js.Array[PayloadError]] = js.native
}

@js.native
trait HandleFieldPayload extends js.Object {
  val args: js.Object = js.native
  val dataID: String = js.native
  val fieldKey: String = js.native
  val handle: String = js.native
  val handleKey: String = js.native

}
