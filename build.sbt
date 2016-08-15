enablePlugins(ScalaJSPlugin)

name := "crowd-agent-simulation-scala-js"

version := "1.0"

scalaVersion := "2.11.8"

scalaJSUseRhino in Global := false

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.0"

skip in packageJSDependencies := false
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"