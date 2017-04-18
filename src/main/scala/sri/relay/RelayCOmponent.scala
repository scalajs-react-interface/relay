package sri.relay

import scala.scalajs.js.annotation.ScalaJSDefined
import sri.core._
import sri.relay.runtime.Disposable
import sri.relay.runtime.store.RelayEnvironment

import scala.scalajs.js
import scala.scalajs.js.|

@ScalaJSDefined
abstract class RelayFragmentComponent[P <: js.Object, S <: AnyRef]
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

@ScalaJSDefined
abstract class RelayFragmentComponentP[P <: js.Object]
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

@ScalaJSDefined
abstract class RelayRefetchComponent[P <: js.Object, S <: AnyRef]
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

@ScalaJSDefined
abstract class RelayRefetchComponentP[P <: js.Object]
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

@ScalaJSDefined
abstract class RelayPaginationComponent[P <: js.Object, S <: AnyRef]
    extends ComponentJS[P, S]
    with RelayPaginationClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline def hasMore: Boolean =
    props.asInstanceOf[js.Dynamic].relay.hasMore.asInstanceOf[Boolean]

  @inline def isLoading: Boolean =
    props.asInstanceOf[js.Dynamic].relay.isLoading.asInstanceOf[Boolean]

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

@ScalaJSDefined
abstract class RelayPaginationComponentP[P <: js.Object]
    extends ComponentJS[P, Null]
    with RelayPaginationClass {
  override type PropsType = P

  @inline def environment =
    props
      .asInstanceOf[js.Dynamic]
      .relay
      .environment
      .asInstanceOf[RelayEnvironment]

  @inline def hasMore: Boolean =
    props.asInstanceOf[js.Dynamic].relay.hasMore.asInstanceOf[Boolean]

  @inline def isLoading: Boolean =
    props.asInstanceOf[js.Dynamic].relay.isLoading.asInstanceOf[Boolean]

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
