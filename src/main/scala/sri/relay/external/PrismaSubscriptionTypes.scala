package sri.relay.external

import scalajsplus.macros.FunctionObjectMacro
import scalajsplus.{OptDefault, OptionalParam}

import scala.scalajs.js

trait SubscriptionFilterNode extends js.Object {

}

trait SubscriptionFilter[FN, MT] extends js.Object  {
  val AND :js.UndefOr[js.Array[SubscriptionFilter[FN, MT]]]  = js.undefined
  val OR :js.UndefOr[js.Array[SubscriptionFilter[FN, MT]]]  = js.undefined
  val mutation_in :js.UndefOr[js.Array[MT]]  = js.undefined
  val updatedFields_contains :js.UndefOr[String]  = js.undefined
  val updatedFields_contains_every :js.UndefOr[js.Array[String]]  = js.undefined
  val updatedFields_contains_some :js.UndefOr[js.Array[String]]  = js.undefined
  val node :js.UndefOr[FN]  = js.undefined
}

object SubscriptionFilter {
  @inline
  def apply[FN <: js.Any, MT](AND :OptionalParam[js.Array[SubscriptionFilter[FN, MT]]]  = OptDefault,
                          OR :OptionalParam[js.Array[SubscriptionFilter[FN, MT]]]  = OptDefault,
                          mutation_in :OptionalParam[js.Array[MT]]  = OptDefault,
                          updatedFields_contains :OptionalParam[String]  = OptDefault,
                          updatedFields_contains_every :OptionalParam[js.Array[String]]  = OptDefault,
                          updatedFields_contains_some :OptionalParam[js.Array[String]]  = OptDefault,
                          node :OptionalParam[FN]  = OptDefault):SubscriptionFilter[FN, MT] = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[SubscriptionFilter[FN, MT]]
  }
}

