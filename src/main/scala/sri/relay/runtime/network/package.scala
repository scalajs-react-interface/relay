package sri.relay.runtime

import org.scalajs.dom.raw.{Blob, File}
import sri.relay.runtime.store.{MutableRecordSource, Observer}

import scalajs.js.{undefined, UndefOr => U}
import scala.scalajs.js
import scala.scalajs.js.{Promise, |}

package object network {

  type FetchFunction = js.Function4[ConcreteBatch /*operation*/,
                                    js.Object /*variables*/,
                                    CacheConfig /*cacheConfig*/,
                                    js.Dictionary[File | Blob] /*uploadables*/,
                                    Promise[QueryPayload]]

  type RequestResponseFunction =
    js.Function4[ConcreteBatch /*operation*/,
                 js.Object /*variables*/,
                 CacheConfig /*cacheConfig*/,
                 js.Dictionary[File | Blob] /*uploadables*/,
                 Promise[RelayResponsePayload]]

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
trait ConcreteBatch extends js.Object {
  val fragment: js.Object = js.native
  val id: js.Object = js.native
  val kind: js.Object = js.native
  val metadata: js.Object = js.native
  val name: js.Object = js.native
  val query :js.Object = js.native
  val text :js.Object = js.native
}

@js.native
trait CacheConfig extends js.Object

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
