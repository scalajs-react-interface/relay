package sri.relay.runtime.subscription

import org.scalajs.dom.raw.{Blob, File}
import scalajsplus.macros.FunctionObjectMacro
import scalajsplus.{OptDefault, OptionalParam}
import sri.relay.runtime.mutation.MutationConfig
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.{RecordSourceSelectorProxy, RelayEnvironment}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("relay-runtime", "requestSubscription")
object RelayRequestSubscription extends js.Object {

  def apply(environment: RelayEnvironment,
            config: SubscriptionClientConfig): Unit =
    js.native

}

trait RequestSubscriptionConfig extends js.Object

object RequestSubscriptionConfig {

  @inline
  def apply(
      subscription: GraphQLTaggedNode,
      variables: js.Object,
      configs: OptionalParam[js.Array[MutationConfig]] = OptDefault,
      uploadables: OptionalParam[js.Dictionary[File | Blob]] = OptDefault,
      onCompleted: OptionalParam[() => Unit] = OptDefault,
      onError: OptionalParam[js.Error => _] = OptDefault,
      onNext: OptionalParam[js.Object => _] = OptDefault,
      updater: OptionalParam[RecordSourceSelectorProxy => Unit] = OptDefault)
    : SubscriptionClientConfig = {

    val p = FunctionObjectMacro()
    p.asInstanceOf[SubscriptionClientConfig]
  }

}
