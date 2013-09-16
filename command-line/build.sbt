name := "command-line"

scalaVersion := "2.10.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")
