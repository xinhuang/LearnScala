lazy val `active-object` = project.dependsOn(RAII)

lazy val aproject = project

lazy val `length-calculator` = project

lazy val `sleeping-barber` = project

lazy val `parking-lot` = project

lazy val RAII = project

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")
