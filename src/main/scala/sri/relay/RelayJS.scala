package sri.relay

import sri.core._
import scalajsplus.macros.{FunctionObjectMacro}
import scalajsplus.{OptDefault, OptionalParam}
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.{RecordSourceProxy, RelayEnvironment}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("react-relay", JSImport.Namespace)
object RelayJS extends js.Object {

  def createFragmentContainer[C <: RelayFragmentClass: js.ConstructorTag](
      component: RelayFragmentComponentConstructor { type ComponentType = C },
      fragmentSpec: GraphQLTaggedNode | js.Dictionary[GraphQLTaggedNode])
    : RelayContainer[C] = js.native

  def createRefetchContainer[C <: RelayRefetchClass: js.ConstructorTag](
      component: RelayRefetchComponentConstructor { type ComponentType = C },
      fragmentSpec: GraphQLTaggedNode | js.Dictionary[GraphQLTaggedNode],
      taggedNode: GraphQLTaggedNode): RelayContainer[C] = js.native

  def createPaginationContainer[C <: RelayPaginationClass: js.ConstructorTag](
      component: RelayPaginationComponentConstructor { type ComponentType = C },
      fragmentSpec: GraphQLTaggedNode | js.Dictionary[GraphQLTaggedNode],
      connectionConfig: ConnectionConfig): RelayContainer[C] = js.native

  def commitLocalUpdate(environment: RelayEnvironment,
                        updater: js.Function1[RecordSourceProxy, Unit]): Unit =
    js.native

}

object Relay {

  def createFragmentContainer[C <: RelayFragmentClass: js.ConstructorTag](
      fragmentSpec: js.Dictionary[GraphQLTaggedNode]): RelayContainer[C] =
    RelayJS.createFragmentContainer(relayFragmentComponentConstructor[C],
                                    fragmentSpec)

  def createRefetchContainer[C <: RelayRefetchClass: js.ConstructorTag](
      fragmentSpec: GraphQLTaggedNode | js.Dictionary[GraphQLTaggedNode],
      taggedNode: GraphQLTaggedNode): RelayContainer[C] =
    RelayJS.createRefetchContainer(relayRefetchComponentConstructor[C],
                                   fragmentSpec,
                                   taggedNode)

  def createPaginationContainer[C <: RelayPaginationClass: js.ConstructorTag](
      fragmentSpec: GraphQLTaggedNode | js.Dictionary[GraphQLTaggedNode],
      connectionConfig: ConnectionConfig): RelayContainer[C] =
    RelayJS.createPaginationContainer(relayPaginationComponentConstructor[C],
                                      fragmentSpec,
                                      connectionConfig)

  def commitLocalUpdate(environment: RelayEnvironment,
                        updater: js.Function1[RecordSourceProxy, Unit]): Unit =
    RelayJS.commitLocalUpdate(environment, updater)

}
@js.native
trait RelayContainer[C <: RelayClass] extends ComponentConstructor {
  override type ComponentType = C
}

trait ConnectionConfig extends js.Object

object ConnectionConfig {

  @inline
  def apply(
      query: GraphQLTaggedNode,
      direction: OptionalParam[ConnectionDirection] = OptDefault,
      getFragmentVariables: OptionalParam[(js.Object, Int) => js.Object] =
        OptDefault,
      getVariables: (js.Object, PaginationInfo, js.Object) => js.Object,
      getConnectionFromProps: OptionalParam[
        js.Object => js.UndefOr[ConnectionData]] = OptDefault)
    : ConnectionConfig = {
    val config = FunctionObjectMacro()
    config.asInstanceOf[ConnectionConfig]
  }
}

trait PaginationInfo extends js.Object {
  val count: Int
  val cursor: js.UndefOr[String]
}

@js.native
trait ConnectionData extends js.Object {}

@js.native
trait ConnectionDirection extends js.Object

object ConnectionDirection {
  @inline def BACKWARD = "backward".asInstanceOf[ConnectionDirection]
  @inline def FORWARD = "forward".asInstanceOf[ConnectionDirection]
}

trait RefetchOptions extends js.Object

object RefetchOptions {

  @inline
  def apply(force: OptionalParam[Boolean] = OptDefault): RefetchOptions = {
    val options = FunctionObjectMacro()
    options.asInstanceOf[RefetchOptions]
  }
}
