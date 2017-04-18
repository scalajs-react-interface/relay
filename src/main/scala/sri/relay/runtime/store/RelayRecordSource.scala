package sri.relay.runtime.store

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import scalajs.js.{UndefOr => U, undefined}

@js.native
trait RelayRecordState extends js.Object {
  val EXISTENT: String = js.native
  val NONEXISTENT: String = js.native
  val UNKNOWN: String = js.native
}

@js.native
trait RecordSource extends js.Object {
  def get(dataID: String): js.Object = js.native
  def getRecordIDs(): js.Array[String] = js.native
  def getStatus(dataID: String): RelayRecordState = js.native
  def has(dataID: String): Boolean = js.native
  def load(dataID: String,
           callback: js.Function2[U[js.Error], U[js.Object], Unit]): Unit =
    js.native
  def size(): Int = js.native
}

@js.native
trait MutableRecordSource extends RecordSource {
  def clear(): Unit = js.native
  def delete(dataID: String): Unit = js.native
  def remove(dataID: String): Unit = js.native
  def set(dataID: String, record: js.Object): Unit = js.native

}

@js.native
@JSImport("relay-runtime", "Store")
class RelayRecordSource(val records: js.Object = ???)
    extends MutableRecordSource {}
