package sri.relay.runtime.store

import sri.macros.{FunctionObjectMacro, OptDefault, OptionalParam}
import sri.relay.runtime.network.RelayNetwork

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@ScalaJSDefined
trait RelayEnvironmentConfig extends js.Object

object RelayEnvironmentConfig {

  @inline
  def apply(store: RelayStore,
            network: RelayNetwork,
            handlerProvider: OptionalParam[js.Object] = OptDefault)
    : RelayEnvironmentConfig = {
    val config = FunctionObjectMacro()
    config.asInstanceOf[RelayEnvironmentConfig]
  }
}

@js.native
@JSImport("relay-runtime", "Environment")
class RelayEnvironment(config: RelayEnvironmentConfig) extends js.Object {}
