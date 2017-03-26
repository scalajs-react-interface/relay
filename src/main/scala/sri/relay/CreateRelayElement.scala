package sri.relay

import sri.core.CreateElementJS
import sri.relay.container.RelayContainer

object CreateRelayElement {

  @inline
  def apply[C <: RelayClass](container: RelayContainer[C],
                             props: C#PropsType) =
    CreateElementJS(container, props)
}
