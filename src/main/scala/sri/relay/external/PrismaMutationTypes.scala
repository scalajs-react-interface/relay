package sri.relay.external

import scala.scalajs.js

@js.native
trait MutationType extends js.Object

object MutationType {
  @inline def CREATED = "CREATED".asInstanceOf[MutationType]
  @inline def UPDATED = "UPDATED".asInstanceOf[MutationType]
  @inline def DELETED = "DELETED".asInstanceOf[MutationType]
}
