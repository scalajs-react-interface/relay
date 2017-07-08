package sri

import sri.core.{ComponentConstructor, ReactScalaClassJS}

import scala.scalajs.js

package object relay {

  type DataID = String

  trait RelayClass extends ReactScalaClassJS {}

  trait RelayFragmentClass extends RelayClass

  trait RelayPaginationClass extends RelayClass

  trait RelayRefetchClass extends RelayClass

  trait RelayComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayClass
  }

  trait RelayFragmentComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayFragmentClass
  }

  trait RelayRefetchComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayFragmentClass
  }

  trait RelayPaginationComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayFragmentClass
  }

  def relayComponentConstructor[C <: RelayClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[RelayComponentConstructor { type ComponentType = C }]

  def relayFragmentComponentConstructor[
      C <: RelayFragmentClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[RelayFragmentComponentConstructor {
        type ComponentType = C
      }]

  def relayRefetchComponentConstructor[
      C <: RelayRefetchClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[RelayRefetchComponentConstructor {
        type ComponentType = C
      }]

  def relayPaginationComponentConstructor[
      C <: RelayPaginationClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[RelayPaginationComponentConstructor {
        type ComponentType = C
      }]
}
