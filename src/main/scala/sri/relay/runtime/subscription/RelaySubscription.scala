package sri.relay.runtime.subscription

import org.scalajs.dom
import sri.relay.external.SubscriptionFilter
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.{RecordSourceSelectorProxy, RelayEnvironment}

import scala.scalajs.js

object RelaySubscription {

  def apply[FN, MT]
  (
    environment: RelayEnvironment,
    query: GraphQLTaggedNode,
    filter: SubscriptionFilter[FN, MT],
    updaterFn: Option[(RecordSourceSelectorProxy) => Unit] = None,
    onNextFn: Option[(js.Object) => Unit] = None,
    onErrorFn: Option[(js.Error) => Unit] = None,
    onCompleteFn: Option[() => Unit] = None
  ) = {

    val defaultU = (_: RecordSourceSelectorProxy) => ()
    val defaultN = (_: js.Object) => ()
    val defaultC = () => (dom.window.console.log("Server closed the subscription."))
    val defaultE = (e: js.Error) => (dom.window.console.log("Error in subscription request", e))

    val u = updaterFn.getOrElse(defaultU)
    val n = onNextFn.getOrElse(defaultN)
    val c = onCompleteFn.getOrElse(defaultC)
    val e = onErrorFn.getOrElse(defaultE)

    RelayRequestSubscription(
      environment,
      RequestSubscriptionConfig(
        subscription = query,
        variables = js.Dynamic.literal(filter = filter),
        onError = e,
        onCompleted = c,
        onNext = n,
        updater = u
      )
    )
  }
}
