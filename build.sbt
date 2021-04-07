name := "tdsecurities-coding-challenge"

version := "1.0"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5" ,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.10" % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
