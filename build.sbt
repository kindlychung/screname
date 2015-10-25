name := "screname"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

mainClass in Compile := Some("Main")

mainClass in assembly := Some("Main")

