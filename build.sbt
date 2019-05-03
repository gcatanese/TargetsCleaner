name := "TargetsCleaner"

version := "0.1"

scalaVersion := "2.12.8"

mainClass := Some("com.kata.Main")

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"

resolvers in ThisBuild ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)




