package sri.relay.runtime.store

import org.scalajs.dom.raw.{Blob, File}
import sri.macros.{FunctionObjectMacro, OptDefault, OptionalParam}
import sri.relay.runtime.{
  CacheConfig,
  ConcreteBatch,
  Disposable,
  RelayResponsePayload
}
import sri.relay.runtime.network.RelayNetwork

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

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
class RelayEnvironment(config: RelayEnvironmentConfig) extends js.Object {

  def getStore(): RelayStore = js.native

  def applyUpdate(updater: js.Function1[RecordSourceProxy, Unit]): Unit =
    js.native

  def commitUpdate(updater: js.Function1[RecordSourceProxy, Unit]): Unit =
    js.native

  def check(readSelector: CSelector[ConcreteSelectableNode]): Boolean =
    js.native

  def lookup(readSelector: CSelector[ConcreteSelectableNode])
    : CSnapshot[ConcreteSelectableNode] =
    js.native

  def commitPayload(
      operationSelector: COperationSelector[ConcreteSelectableNode,
                                            ConcreteBatch],
      payload: js.Object
  ): Unit = js.native

  def subscribe(
      snapshot: CSnapshot[ConcreteSelectableNode],
      callback: js.Function1[CSnapshot[ConcreteSelectableNode], Unit])
    : Disposable = js.native

  def retain(selector: CSelector[ConcreteSelectableNode]): Disposable =
    js.native

  def sendQuery(input: SendQueryInput): Disposable = js.native

  def streamQuery(input: StreamQueryInput): Disposable = js.native

  def sendMutation(
      input: SendMutationInput
  ): Disposable = js.native

  def sendSubscription(
      input: SendSubscriptionInput
  ): Disposable = js.native

}

trait SendQueryInput extends js.Object

object SendQueryInput {

  @inline
  def apply(cacheConfig: OptionalParam[CacheConfig] = OptDefault,
            onCompleted: OptionalParam[Function0[_]] = OptDefault,
            onError: OptionalParam[Function1[js.Error, Unit]] = OptDefault,
            onNext: OptionalParam[Function1[RelayResponsePayload, Unit]] =
              OptDefault,
            operation: COperationSelector[ConcreteSelectableNode,
                                          ConcreteBatch]): SendQueryInput = {

    val p = FunctionObjectMacro()
    p.asInstanceOf[SendQueryInput]

  }

}

trait StreamQueryInput extends js.Object

object StreamQueryInput {

  @inline
  def apply(cacheConfig: OptionalParam[CacheConfig] = OptDefault,
            onCompleted: OptionalParam[Function0[_]] = OptDefault,
            onError: OptionalParam[Function1[js.Error, Unit]] = OptDefault,
            onNext: OptionalParam[Function1[RelayResponsePayload, Unit]] =
              OptDefault,
            operation: COperationSelector[ConcreteSelectableNode,
                                          ConcreteBatch]): StreamQueryInput = {

    val p = FunctionObjectMacro()
    p.asInstanceOf[StreamQueryInput]

  }
}

trait SendMutationInput extends js.Object

object SendMutationInput {

  @inline
  def apply(
      onCompleted: OptionalParam[Function0[_]] = OptDefault,
      onError: OptionalParam[Function1[js.Error, Unit]] = OptDefault,
      optimisticResponse: OptionalParam[js.Object] = OptDefault,
      optimisticUpdater: OptionalParam[
        Function2[RecordSourceSelectorProxy, js.Object, Unit]] = OptDefault,
      updater: js.Function2[RecordSourceSelectorProxy, js.Object, Unit] = ???,
      uploadables: OptionalParam[js.Dictionary[File | Blob]] = OptDefault,
      operation: COperationSelector[ConcreteSelectableNode, ConcreteBatch])
    : SendMutationInput = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[SendMutationInput]

  }
}

trait SendSubscriptionInput extends js.Object

object SendSubscriptionInput {

  @inline
  def apply(onCompleted: OptionalParam[Function0[_]] = OptDefault,
            onError: OptionalParam[Function1[js.Error, Unit]] = OptDefault,
            updater: js.Function2[RecordSourceSelectorProxy, js.Object, Unit] =
              ???,
            onNext: OptionalParam[Function1[RelayResponsePayload, Unit]] =
              OptDefault,
            operation: COperationSelector[ConcreteSelectableNode,
                                          ConcreteBatch])
    : SendSubscriptionInput = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[SendSubscriptionInput]
  }
}

trait CSelector[TNode] extends js.Object {
  val dataID: String
  val node: TNode
  val variables: js.Object
}

trait CSnapshot[TNode] extends CSelector[TNode] {
  val data: js.UndefOr[js.Object] = js.undefined
  val seenRecords: js.Dictionary[js.Dictionary[js.Any]]
}

trait COperationSelector[TNode, TOperation] extends js.Object {
  val fragment: CSelector[TNode]
  val node: TOperation
  val root: CSelector[TNode]
  val variables: js.Object
}

trait ConcreteFragment extends js.Object

trait ConcreteRoot extends js.Object
