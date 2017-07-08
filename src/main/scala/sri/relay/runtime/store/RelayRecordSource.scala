package sri.relay.runtime.store

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{UndefOr => U}

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
trait RecordProxy extends js.Object {

  def copyFieldsFrom(source: RecordProxy): Unit = js.native
  def getDataID(): String = js.native
  def getLinkedRecord(name: String,
                      args: js.Object = ???): js.UndefOr[RecordProxy] =
    js.native
  def getLinkedRecords(
      name: String,
      args: js.Object = ???): js.UndefOr[js.Array[js.UndefOr[RecordProxy]]] =
    js.native

  def getOrCreateLinkedRecord(name: String,
                              typeName: String,
                              args: js.Object = ???): RecordProxy = js.native

  def getType(): String = js.native

  def getValue(name: String, args: js.Object = ???): js.Any = js.native

  def setLinkedRecord(record: RecordProxy,
                      name: String,
                      args: js.Object = ???): RecordProxy = js.native

  def setLinkedRecords(records: js.Array[RecordProxy],
                       name: String,
                       args: js.Object = ???): RecordProxy = js.native

  def setValue(value: js.Any,
               name: String,
               args: js.Object = ???): RecordProxy = js.native

}
@js.native
trait RecordSourceProxy extends js.Object {
  def create(dataID: String, typeName: String): RecordProxy = js.native
  def delete(dataID: String): Unit = js.native
  def get(dataID: String): js.UndefOr[RecordProxy] = js.native
  def getRoot(): RecordProxy = js.native

}

@js.native
trait RecordSourceSelectorProxy extends js.Object {
  def create(dataID: String, typeName: String): RecordProxy = js.native
  def delete(dataID: String): Unit = js.native
  def get(dataID: String): js.UndefOr[RecordProxy] = js.native
  def getRootField(fieldName: String): js.UndefOr[RecordProxy] = js.native
  def getPluralRootField(
      fieldName: String): js.UndefOr[js.Array[js.UndefOr[RecordProxy]]]
}
@js.native
trait MutableRecordSource extends RecordSource {
  def clear(): Unit = js.native
  def delete(dataID: String): Unit = js.native
  def remove(dataID: String): Unit = js.native
  def set(dataID: String, record: js.Object): Unit = js.native
  def getRoot(): RecordProxy = js.native
}

@js.native
@JSImport("relay-runtime", "RecordSource")
class RelayRecordSource(val records: js.Object = ???)
    extends MutableRecordSource {}
