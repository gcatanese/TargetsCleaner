package com.kata


class ParameterValidator {
  def validateParameters(args: Array[String]): Unit = {
    if (args == null || args.isEmpty) {
      throw new IllegalArgumentException("Folder path parameter is missing")
    }

    if (args.length > 2) {
      println("Invalid number of parameters")
    }

    if (args.length > 2) {
      throw new IllegalArgumentException("Invalid number of parameters")
    }

  }

}

