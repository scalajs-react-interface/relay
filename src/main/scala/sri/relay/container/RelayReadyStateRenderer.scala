package sri.relay.container

import sri.core._
import sri.macros.FunctionObjectMacro
import sri.relay.{Relay, RelayClass, RelayComponentProps}
import sri.relay.route.RelayQueryConfig
import sri.relay.store.RelayEnvironment
import sri.relay.tools.ReadyState

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{undefined, UndefOr => U}

object RelayReadyStateRenderer {
  def apply[C <: RelayClass](
      key: U[String] = undefined,
      ref: U[RelayReadyStateRendererM => _] = undefined,
      Container: RelayContainer[C],
      environment: RelayEnvironment,
      queryConfig: RelayQueryConfig,
      readyState: U[ReadyState] = undefined,
      render: U[RelayRenderArgs[C#PropsType] => ReactElement] = undefined,
      retry: U[() => _] = undefined)(children: ReactNode*): ReactElement = {
    val props = FunctionObjectMacro()
    React.createElement(Relay.ReadyStateRenderer, props, children: _*)
  }

}
@js.native
trait RelayReadyStateRendererM extends js.Object

@ScalaJSDefined
trait RelayRenderArgs[P] extends js.Object {

  val done: Boolean
  val error: U[js.Dynamic]
  val props: U[P]
  val retry: U[js.Function0[_]]
  val stale: Boolean
}
