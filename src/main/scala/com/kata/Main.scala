package com.kata

import java.io.File

object Main {

  var preview = true
  var count = 0;

  def main(args: Array[String]): Unit = {

    if (args == null || args.isEmpty) {
      println("Folder path parameter is missing")
      System.exit(0)
      return
    }

    if (args.length > 2) {
      println("Invalid number of parameters")
      return
    }

    var path = args(0)

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

  private def visit(folder: File): Unit = {

    var folders = getListOfFolders(folder)

    folders.foreach { folder =>
      //println(folder.getName)
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

  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      println("Unable to delete" + file)
  }
}
