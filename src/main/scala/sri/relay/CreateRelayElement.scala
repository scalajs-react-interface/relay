package sri.relay

import sri.core.CreateElementJS

object CreateRelayElement {

  @inline
  def apply[C <: RelayClass](container: RelayContainer[C],
                             props: C#PropsType) =
    CreateElementJS(container, props)
}
