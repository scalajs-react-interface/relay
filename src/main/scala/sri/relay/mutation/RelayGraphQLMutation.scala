package sri.relay.mutation

import sri.relay.Relay
import sri.relay.query.RelayQL.RelayConcreteNode
import sri.relay.store.RelayEnvironment
import sri.relay.tools.RelayMutationTransactionCommitCallbacks
import sri.relay.tools.RelayTypes.RelayMutationConfig

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-relay", "GraphQLMutation")
class RelayGraphQLMutation(
    val mutation: RelayConcreteNode,
    val variables: js.Object,
    val files: js.Any,
    val environment: RelayEnvironment,
    val callbacks: RelayMutationTransactionCommitCallbacks = ???,
    val collisionKey: String = ???)
    extends js.Object {

  def applyOptimistic(
      optimisticQuery: RelayConcreteNode,
      optimisticResponse: js.Object,
      configs: js.Array[RelayMutationConfig] = ???): RelayMutationTransaction =
    js.native

  def commit(
      configs: js.Array[RelayMutationConfig] = ???): RelayMutationTransaction =
    js.native

  def rollback(): Unit = js.native
}

@js.native
@JSImport("react-relay", "GraphQLMutation")
object RelayGraphQLMutation extends js.Object {
  def create(mutation: RelayConcreteNode,
             variables: js.Object,
             environment: RelayEnvironment): RelayGraphQLMutation = js.native
  def createWithFiles(mutation: RelayConcreteNode,
                      variables: js.Object,
                      files: js.Any,
                      environment: RelayEnvironment): RelayGraphQLMutation =
    js.native
}
