package com.kata

import java.io.File

/**
  * TargetsCleaner tool: removed all Maven 'target' folders provided a given path
  *
  * It can also run in PREVIEW mode to list the folders that will be deleted
  */
object Main {

  var preview = true // run in PREVIEW mode as default
  var count = 0;

  /**
    * Main entry point
    * @param args
    */
  def main(args: Array[String]): Unit = {

    var validator = new ParameterValidator().validateParameters(args)

    val path = args(0)

    if (args.length == 2 && args(1) != null && args(1).equalsIgnoreCase("false")) {
      preview = false
    }

    println("*** [Parameters]")
    println("path: " + path)
    println("preview: " + preview)
    println("***")

    if (!exists(path)) return

    visit(new java.io.File(path))

    println("Total " + count)

  }


  /**
    * Visit recursively the given path
    * @param folder
    */
  private def visit(folder: File): Unit = {

    var folders = getListOfFolders(folder)

    folders.foreach { folder =>
      if (folder.getName.equals("target")) {
        delete(folder)
      } else {
        visit(folder)
      }
    }
  }

  private def delete(folder: File): Unit = {

    if (preview) {
      println("PREVIEW: Folder " + folder + " will be deleted")
    } else {
      deleteRecursively(folder)
      println("Folder " + folder + " HAS BEEN deleted")
    }

    count += 1;
  }

  private def exists(path: String) = {
    val exist = new java.io.File(path).exists
    if (!exist) {
      println("Path does not exist")
    }
    exist
  }

  def getListOfFolders(dir: File): List[File] = dir.listFiles.filter(_.isDirectory).toList

  /**
    * Delete a folder and its content
    * @param file
    */
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      println("Unable to delete" + file)
  }
}
