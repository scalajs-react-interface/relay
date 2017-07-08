package sri.relay

import scala.scalajs.js
import scala.scalajs.js.|

package object runtime {

  type CallValue =
    Boolean | Double | Int | String | js.Array[Any] | js.Dictionary[Any]

  type RangeBehaviorsFunction =
    js.Function1[js.Dictionary[CallValue], js.Object]

  type RangeBehaviorsObject = js.Dictionary[js.Object]

  type RangeBehaviors = RangeBehaviorsFunction | RangeBehaviorsObject

}
