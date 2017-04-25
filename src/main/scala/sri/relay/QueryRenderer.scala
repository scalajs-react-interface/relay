package sri.relay

import sri.core.{CreateElementJSNoInline, JSComponent, ReactElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import sri.macros.{
  FunctionObjectMacro,
  OptDefault => NoValue,
  OptionalParam => U
}
import sri.relay.runtime.CacheConfig
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.RelayEnvironment

@js.native
@JSImport("react-relay", "QueryRenderer")
object ReactRelayQueryRenderer extends JSComponent[js.Object]

object QueryRenderer {

  @inline
  def apply(cacheConfig: U[CacheConfig] = NoValue,
            environment: RelayEnvironment,
            query: GraphQLTaggedNode,
            render: (ReadyState, js.UndefOr[ReadyState]) => ReactElement,
            variables: U[js.Object] = NoValue)
    : ReactElement { type Instance = ReactRelayQueryRenderer.type } = {

    val p = FunctionObjectMacro()
    CreateElementJSNoInline[ReactRelayQueryRenderer.type](
      ReactRelayQueryRenderer,
      props = p)
  }
}

@js.native
trait ReadyState extends js.Object {
  val error: js.UndefOr[js.Error] = js.native
  val props: js.UndefOr[js.Object] = js.native
  val retry: js.UndefOr[js.Function0[Unit]] = js.native
}
