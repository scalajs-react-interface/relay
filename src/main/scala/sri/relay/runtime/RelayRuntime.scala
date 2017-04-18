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

@js.native
trait RecordProxy extends js.Object {

  def copyFieldsFrom(source: RecordProxy): Unit = js.native

  def getDataID(): String = js.native

  def getLinkedRecord(name: String, args: js.Object = ???): RecordProxy =
    js.native

  def getLinkedRecords(
      name: String,
      args: js.Object = ???): js.UndefOr[js.Array[RecordProxy]] = js.native

  def getOrCreateLinkedRecord(name: String,
                              typeName: String,
                              args: js.Object = ???): RecordProxy = js.native

  def getType(): String = js.native

  def getValue(name: String, args: js.Object = ???): js.Dynamic = js.native

  def setValue(name: String, args: js.Object = ???): js.Dynamic = js.native

  def setLinkedRecord(record: RecordProxy,
                      name: String,
                      args: js.Object = ???): RecordProxy = js.native
  def setLinkedRecords(records: js.Array[RecordProxy],
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
