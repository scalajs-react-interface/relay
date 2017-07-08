package sri.relay

import sri.core.{CreateElementJSNoInline, JSComponent, ReactElement}
import sri.macros.{
  FunctionObjectMacro,
  OptDefault => NoValue,
  OptionalParam => U
}
import sri.relay.runtime.CacheConfig
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.RelayEnvironment

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-relay", "QueryRenderer")
object ReactRelayQueryRenderer extends JSComponent[js.Object]

object QueryRenderer {

  @inline
  def apply[T <: js.Object](cacheConfig: U[CacheConfig] = NoValue,
                            environment: RelayEnvironment,
                            query: GraphQLTaggedNode,
                            render: js.Function2[ReadyState[T],
                                                 js.UndefOr[ReadyState[T]],
                                                 ReactElement],
                            variables: U[js.Object] = NoValue)
    : ReactElement { type Instance = ReactRelayQueryRenderer.type } = {

    val p = FunctionObjectMacro()
    CreateElementJSNoInline[ReactRelayQueryRenderer.type](
      ReactRelayQueryRenderer,
      props = p)
  }
}

@js.native
trait ReadyState[T <: js.Object] extends js.Object {
  val error: js.UndefOr[js.Error] = js.native
  val props: js.UndefOr[T] = js.native
  val retry: js.UndefOr[js.Function0[Unit]] = js.native
}
