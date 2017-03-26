package sri.relay

import scala.scalajs.js.annotation.ScalaJSDefined
import sri.core._
import sri.relay.tools.RelayProp

import scala.scalajs.js

@ScalaJSDefined
abstract class RelayComponent[P <: RelayComponentProps, S <: AnyRef]
    extends ComponentJS[P, S]
    with RelayClass {
  override type PropsType = P
}

@ScalaJSDefined
abstract class RelayComponentP[P <: RelayComponentProps]
    extends ComponentJS[P, Null]
    with RelayClass {
  override type PropsType = P
}

@ScalaJSDefined
abstract class RelayComponentProps extends js.Object {
  val relay: RelayProp = null
}
