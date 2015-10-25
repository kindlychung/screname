name := "screname"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.6.4" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

mainClass in Compile := Some("Main")

mainClass in assembly := Some("Main")

