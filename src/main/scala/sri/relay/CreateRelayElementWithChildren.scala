package sri.relay

import sri.core.{CreateElementJS, ReactNode}
import sri.relay.container.RelayContainer

import scala.scalajs.js

object CreateRelayElementWithChildren {

  @inline
  def apply[C <: RelayClass](container: RelayContainer[C],
                             props: C#PropsType,
                             children: js.Array[ReactNode]) =
    CreateElementJS(container, props, children = children)
}
