ThisBuild / scalaVersion := "2.13.14"
ThisBuild / organization := "io.github.smf"
ThisBuild / version := "0.1.0-SNAPSHOT"

name := "algorithms-scala"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test

testFrameworks += new TestFramework("munit.Framework")
