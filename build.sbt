lazy val `active-object` = project.dependsOn(RAII)

lazy val aproject = project

lazy val `length-calculator` = project

lazy val `sleeping-barber` = project

lazy val `parking-lot` = project

lazy val RAII = project

lazy val yaffuts = project

lazy val `command-line` = project

lazy val observer = project

lazy val di = project

// common properties

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")
