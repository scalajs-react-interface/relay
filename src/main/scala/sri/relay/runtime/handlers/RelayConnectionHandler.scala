package sri.relay.runtime.handlers

import sri.macros.FunctionObjectMacro
import sri.relay.runtime.store.{RecordProxy, RecordSourceProxy}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("relay-runtime", "ConnectionHandler")
object RelayConnectionHandler extends js.Object {

  def update(store: RecordSourceProxy, payload: HandleFieldPayload): Unit =
    js.native

  def getConnection(record: RecordProxy,
                    key: String,
                    filters: js.Object = ???): js.UndefOr[RecordProxy] =
    js.native

  def buildConnectionEdge(store: RecordSourceProxy,
                          connection: RecordProxy,
                          edge: RecordProxy = ???): js.UndefOr[RecordProxy] =
    js.native

  def insertEdgeAfter(record: RecordProxy,
                      newEdge: RecordProxy,
                      cursor: String = ???): Unit = js.native

  def insertEdgeBefore(record: RecordProxy,
                       newEdge: RecordProxy,
                       cursor: String = ???): Unit = js.native

  def mergeEdges(sourceEdges: js.Array[RecordProxy],
                 targetEdges: js.Array[RecordProxy],
                 nodeIDs: js.Array[String]): Unit = js.native

  def deleteNode(record: RecordProxy, nodeID: String): Unit = js.native

  def createEdge(store: RecordSourceProxy,
                 record: RecordProxy,
                 node: RecordProxy,
                 edgeType: String): RecordProxy = js.native
}

trait HandleFieldPayload extends js.Object

object HandleFieldPayload {

  @inline
  def apply(args: js.Object,
            dataID: String,
            fieldKey: String,
            handle: String,
            handleKey: String): HandleFieldPayload = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[HandleFieldPayload]
  }

}
