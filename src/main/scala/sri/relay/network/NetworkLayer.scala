package sri.relay.network

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.{JSImport, JSName, ScalaJSDefined}

@js.native
trait GraphQLError extends js.Object {
  val message: String = js.native
  val locations: js.UndefOr[js.Array[GraphQLErrorLocation]] = js.native
}

@js.native
trait GraphQLErrorLocation extends js.Object {
  val column: Int = js.native
  val line: Int = js.native
}

@ScalaJSDefined
trait NetworkLayer extends js.Object {
  def sendMutation(mutationRequest: RelayMutationRequest): Promise[Unit]
  def sendQueries(queryRequests: js.Array[RelayQueryRequest]): Promise[Unit]
  def supports(options: js.Array[String]): Boolean
}

@js.native
@JSImport("react-relay", "DefaultNetworkLayer")
class DefaultNetworkLayer(val url: js.UndefOr[String] = js.undefined,
                          config: js.UndefOr[js.Object] = js.undefined)
    extends NetworkLayer {
  override def sendMutation(
      mutationRequest: RelayMutationRequest): Promise[Unit] = js.native

  override def sendQueries(
      queryRequests: js.Array[RelayQueryRequest]): Promise[Unit] = js.native

  override def supports(options: js.Array[String]): Boolean = js.native
}
