package sri.relay.external

import scalajsplus.macros.FunctionObjectMacro
import scalajsplus.{OptDefault, OptionalParam => OP}
import sri.relay.runtime.QueryPayload

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{UndefOr => U}

@js.native
trait ExecutionResult extends js.Object {
  val errors: U[js.Array[_]] = js.native
  val data: U[js.Object] = js.native
}

@js.native
trait WSSubscription extends js.Object {
  def unsubscribe(): Unit = js.native
}

@js.native
trait WSObserver[T] extends js.Object {
  def complete: U[() => Unit] = js.native
  def error: U[js.Error => Unit] = js.native
  def next: U[T => Unit] = js.native
}

object WSObserver {

  @inline
  def apply[T](
             complete: OP[() => Unit] = OptDefault,
             error: OP[js.Error => Unit] = OptDefault,
             next: OP[T => Unit] = OptDefault): WSObserver[T] = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[WSObserver[T]]
  }

}

@js.native
trait WSObservable[T] extends js.Object {
  def subscribe(observer: WSObserver[T]): WSSubscription = js.native
}


@js.native
@JSImport("subscriptions-transport-ws", "SubscriptionClient")
class SubscriptionWebSocketClient(
                                   url: String,
                                   config: SubscriptionClientConfig)
  extends js.Object {

  def request(request: OperationOptions): WSObservable[QueryPayload] = js.native

}

trait SubscriptionClientConfig extends js.Object

object SubscriptionClientConfig {

  @inline
  def apply(
             connectionParams: OP[js.Object] = OptDefault,
             timeout: OP[Int] = OptDefault,
             reconnect: OP[Boolean] = OptDefault,
             reconnectionAttempts: OP[Int] = OptDefault,
             connectionCallback: OP[(js.Array[js.Error], js.Any) => _] = OptDefault,
             `lazy`: OP[Int] = OptDefault,
             inactivityTimeout: OP[Int] = OptDefault)
  : SubscriptionClientConfig = {

    val p = FunctionObjectMacro()
    p.asInstanceOf[SubscriptionClientConfig]
  }

}

trait OperationOptions extends js.Object

object OperationOptions {

  @inline
  def apply(
                query: OP[String] = OptDefault,
                variables: OP[js.Object] = OptDefault,
                operationName: OP[String] = OptDefault)
  : OperationOptions = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[OperationOptions]
  }

}
