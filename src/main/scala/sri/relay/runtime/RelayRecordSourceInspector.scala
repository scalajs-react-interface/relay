package sri.relay.runtime

import sri.relay.runtime.store.RelayRecordSource

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("relay-runtime", "RecordSourceInspector")
class RelayRecordSourceInspector(val source: RelayRecordSource)
    extends js.Object {

  def get(dataID: String): js.UndefOr[RecordInspector] = js.native

  def getNodes(): js.Array[RecordSummary] = js.native

  def getRecords(): js.Array[RecordSummary] = js.native

  def getRoot(): RecordInspector = js.native

}
@js.native
trait RecordInspector extends js.Object {

  def getDataID(): String = js.native

  def getFields(): js.Array[String] = js.native

  def getType(): String = js.native

  def inspect(): js.Dynamic = js.native

  def getValue(name: String, args: js.Object = ???): js.Dynamic = js.native

  def getLinkedRecord(name: String, args: js.Object = ???): RecordInspector =
    js.native

  def getLinkedRecords(
      name: String,
      args: js.Object = ???): js.UndefOr[js.Array[RecordInspector]] =
    js.native

}

@js.native
trait RecordSummary extends js.Object {

  val id: String = js.native

  val `type`: String = js.native

  def createFromRecord(id: String, record: js.Object = ???): RecordSummary =
    js.native

}
