package sri.relay.runtime.mutation

import org.scalajs.dom.raw.{Blob, File}
import scalajsplus.macros.{FunctionObjectMacro}
import scalajsplus.{OptDefault, OptionalParam}
import sri.relay.runtime.{PayloadError, RangeBehaviors}
import sri.relay.runtime.query.GraphQLTaggedNode
import sri.relay.runtime.store.{RecordSourceSelectorProxy, RelayEnvironment}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("relay-runtime", "commitMutation")
object RelayCommitMutation extends js.Object {

  def apply[T](environment: RelayEnvironment,
               config: CommitMutationConfig[T]): Unit =
    js.native

}

trait CommitMutationConfig[T] extends js.Object

object CommitMutationConfig {

  @inline
  def apply[T](
      mutation: GraphQLTaggedNode,
      variables: js.Object,
      configs: OptionalParam[js.Array[MutationConfig]] = OptDefault,
      uploadables: OptionalParam[js.Dictionary[File | Blob]] = OptDefault,
      onCompleted: OptionalParam[
        (T, js.UndefOr[js.Array[PayloadError]]) => Unit] = OptDefault,
      onError: OptionalParam[js.Error => _] = OptDefault,
      optimisticResponse: OptionalParam[js.Object] = OptDefault,
      optimisticUpdater: OptionalParam[(RecordSourceSelectorProxy) => Unit] =
        OptDefault,
      updater: OptionalParam[(RecordSourceSelectorProxy) => Unit] = OptDefault)
    : CommitMutationConfig[T] = {

    val p = FunctionObjectMacro()
    p.asInstanceOf[CommitMutationConfig[T]]
  }

}

trait MutationConfig extends js.Object

object MutationConfig {

  @inline
  def apply(): MutationConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[MutationConfig]
  }

}

trait FieldsChangeConfig extends MutationConfig

object FieldsChangeConfig {

  @inline
  def apply(fieldIDs: js.Dictionary[String | js.Array[String]])
    : FieldsChangeConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[js.Dynamic].updateDynamic("type")("FIELDS_CHANGE")
    p.asInstanceOf[FieldsChangeConfig]
  }

}

trait RequiredChildrenConfig extends MutationConfig

object RequiredChildrenConfig {

  @inline
  def apply(children: js.Array[Any]): RequiredChildrenConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[js.Dynamic].updateDynamic("type")("REQUIRED_CHILDREN")
    p.asInstanceOf[RequiredChildrenConfig]
  }

}

trait NodeDeleteConfig extends MutationConfig

object NodeDeleteConfig {

  @inline
  def apply(parentName: OptionalParam[String] = OptDefault,
            parentID: OptionalParam[String] = OptDefault,
            connectionName: OptionalParam[String] = OptDefault,
            deletedIDFieldName: String): NodeDeleteConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[js.Dynamic].updateDynamic("type")("NODE_DELETE")
    p.asInstanceOf[NodeDeleteConfig]
  }

}

trait RangeAddConfig extends MutationConfig

object RangeAddConfig {

  @inline
  def apply(parentName: OptionalParam[String] = OptDefault,
            parentID: OptionalParam[String] = OptDefault,
            connectionName: OptionalParam[String] = OptDefault,
            connectionInfo: OptionalParam[js.Array[ConnectionInfo]] =
              OptDefault,
            rangeBehaviors: OptionalParam[RangeBehaviors] = OptDefault,
            edgeName: String): RangeAddConfig = {
    import scalajsplus.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[js.Dynamic].updateDynamic("type")("RANGE_ADD")
    p.asInstanceOf[RangeAddConfig]
  }

}

trait RangeDeleteConfig extends MutationConfig

object RangeDeleteConfig {

  @inline
  def apply(
      parentName: OptionalParam[String] = OptDefault,
      parentID: OptionalParam[String] = OptDefault,
      connectionName: OptionalParam[String] = OptDefault,
      connectionKeys: OptionalParam[js.Array[ConnectionKey]] = OptDefault,
      deletedIDFieldName: OptionalParam[String | js.Array[String]] = OptDefault,
      pathToConnection: js.Array[String]): RangeDeleteConfig = {
    import scalajsplus.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[js.Dynamic].updateDynamic("type")("RANGE_DELETE")
    p.asInstanceOf[RangeDeleteConfig]
  }

}

trait ConnectionInfo extends js.Object

object ConnectionInfo {

  @inline
  def apply(key: String,
            rangeBehavior: String,
            filters: OptionalParam[js.Object] = OptDefault): ConnectionInfo = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[ConnectionInfo]
  }

}

trait ConnectionKey extends js.Object

object ConnectionKey {

  @inline
  def apply(): ConnectionKey = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[ConnectionKey]
  }
}
