package sri.relay.container

import sri.core._
import sri.macros.FunctionObjectMacro
import sri.relay.tools.RelayInternalTypes.RelayQuerySet
import sri.relay.{Relay, RelayClass, RelayComponentProps}
import sri.relay.route.RelayQueryConfig
import sri.relay.store.RelayEnvironment
import sri.relay.tools.{Abortable, ReadyState}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{undefined, UndefOr => U}

object RelayRenderer {
  @inline
  def apply[C <: RelayClass](
      key: U[String] = undefined,
      ref: U[RelayRendererM => _] = undefined,
      forceFetch: U[Boolean] = undefined,
      Container: RelayContainer[C],
      environment: RelayEnvironment,
      onForceFetch: U[
        (RelayQuerySet, js.Function1[ReadyState, _]) => Abortable] = undefined,
      onPrimeCache: U[
        (RelayQuerySet, js.Function1[ReadyState, _]) => Abortable] = undefined,
      onReadyStateChange: U[ReadyState => _] = undefined,
      queryConfig: RelayQueryConfig,
      render: U[RelayRenderArgs[C#PropsType] => ReactElement] = undefined)
    : ReactElement = {
    val props = FunctionObjectMacro()
    React.createElement(Relay.Renderer, props)
  }

}

@js.native
trait RelayRendererM extends js.Object
