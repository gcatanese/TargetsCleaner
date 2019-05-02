package com.kata

import org.scalatest._
import Matchers._

class ParameterValidatorTest extends FlatSpec with BeforeAndAfter {

  var validator: ParameterValidator = new ParameterValidator

  an[IllegalArgumentException] should be thrownBy validator.validateParameters(null)

  // test checking exception message
  //  the [IllegalArgumentException] thrownBy {
  //    validator.validateParameters(null)
  //  } should have message "Folder path parameter is missing"

  an[IllegalArgumentException] should be thrownBy validator.validateParameters(new Array[String](3))

  "Parameter validation " should "be fine with one parameter" in {
    var z: Array[String] = new Array[String](1)
    z(0) = "/tmp"

    validator.validateParameters(z)
  }

  it should ("be fine with two parameters") in {
    var x: Array[String] = new Array[String](2)
    x(0) = "/tmp"
    x(1) = "false"

    validator.validateParameters(x)
  }

}
