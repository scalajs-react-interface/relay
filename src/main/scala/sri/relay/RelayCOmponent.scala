package sri.relay

import sri.core._
import sri.relay.runtime.Disposable
import sri.relay.runtime.store.RelayEnvironment

import scala.scalajs.js
import scala.scalajs.js.|

abstract class RelayFragmentComponent[P >: Null <: js.Object,
                                      S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends ComponentJS[P, S]
    with RelayFragmentClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]
}

abstract class RelayFragmentComponentP[P >: Null <: js.Object](
    implicit ev: =:!=[P, Null])
    extends ComponentJS[P, Null]
    with RelayFragmentClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]
}

abstract class RelayRefetchComponent[P >: Null <: js.Object,
                                     S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends ComponentJS[P, S]
    with RelayFragmentClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline
  def refetch(
      refetchVariables: js.Object | js.Function1[js.Object, js.Object],
      renderVariables: js.UndefOr[js.Object] = js.undefined,
      callback: js.UndefOr[js.Function1[js.Error, Unit]] = js.undefined,
      options: js.UndefOr[RefetchOptions] = js.undefined): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .refetch(refetchVariables.asInstanceOf[js.Any],
               renderVariables,
               callback,
               options)
      .asInstanceOf[Disposable]
}

abstract class RelayRefetchComponentP[P >: Null <: js.Object](
    implicit ev: =:!=[P, Null])
    extends ComponentJS[P, Null]
    with RelayFragmentClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline
  def refetch(
      refetchVariables: js.Object | js.Function1[js.Object, js.Object],
      renderVariables: js.UndefOr[js.Object] = js.undefined,
      callback: js.UndefOr[js.Function1[js.Error, Unit]] = js.undefined,
      options: js.UndefOr[RefetchOptions] = js.undefined): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .refetch(refetchVariables.asInstanceOf[js.Any],
               renderVariables,
               callback,
               options)
      .asInstanceOf[Disposable]
}

abstract class RelayPaginationComponent[P >: Null <: js.Object,
                                        S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends ComponentJS[P, S]
    with RelayPaginationClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline def hasMore(): Boolean =
    props.asInstanceOf[js.Dynamic].relay.hasMore().asInstanceOf[Boolean]

  @inline def isLoading(): Boolean =
    props.asInstanceOf[js.Dynamic].relay.isLoading().asInstanceOf[Boolean]

  @inline
  def loadMore(pageSize: Int,
               callback: js.Function1[js.Error, Unit]): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .loadMore(pageSize, callback)
      .asInstanceOf[Disposable]

  @inline
  def refetchConnection(totalCount: Int,
                        callback: js.Function1[js.Error, Unit]): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .refetchConnection(totalCount, callback)
      .asInstanceOf[Disposable]
}

abstract class RelayPaginationComponentP[P >: Null <: js.Object](
    implicit ev: =:!=[P, Null])
    extends ComponentJS[P, Null]
    with RelayPaginationClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline def hasMore(): Boolean =
    props.asInstanceOf[js.Dynamic].relay.hasMore().asInstanceOf[Boolean]

  @inline def isLoading(): Boolean =
    props.asInstanceOf[js.Dynamic].relay.isLoading().asInstanceOf[Boolean]

  @inline
  def loadMore(pageSize: Int,
               callback: js.Function1[js.Error, Unit]): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .loadMore(pageSize, callback)
      .asInstanceOf[Disposable]

  @inline
  def refetchConnection(totalCount: Int,
                        callback: js.Function1[js.Error, Unit]): Disposable =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .refetchConnection(totalCount, callback)
      .asInstanceOf[Disposable]
}
