package sri

import sri.core.{ComponentConstructor, ReactScalaClassJS}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

package object relay {

  type DataID = String

  @ScalaJSDefined
  trait RelayClass extends ReactScalaClassJS {}

  @ScalaJSDefined
  trait RelayFragmentClass extends RelayClass

  @ScalaJSDefined
  trait RelayPaginationClass extends RelayClass

  @ScalaJSDefined
  trait RelayRefetchClass extends RelayClass

  @ScalaJSDefined
  trait RelayComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayClass
  }

  @ScalaJSDefined
  trait RelayFragmentComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayFragmentClass
  }

  @ScalaJSDefined
  trait RelayRefetchComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayFragmentClass
  }

  @ScalaJSDefined
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
