package sri

import sri.core.{ComponentConstructor, ReactScalaClassJS}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

package object relay {

  @ScalaJSDefined
  trait RelayClass extends ReactScalaClassJS {
    override type PropsType <: RelayComponentProps
  }

  @ScalaJSDefined
  trait RelayComponentConstructor extends ComponentConstructor {
    override type ComponentType <: RelayClass
  }

  def relayComponentConstructor[C <: RelayClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[RelayComponentConstructor { type ComponentType = C }]
}
