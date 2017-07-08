package sri.relay

import sri.core.CreateElementJS

import scala.scalajs.js.|

object CreateRelayElement {

  @inline
  def apply[C <: RelayClass](container: RelayContainer[C],
                             props: C#PropsType,
                             key: String | Int = null) =
    CreateElementJS(container, props, key = key)
}
