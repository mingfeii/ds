ThisBuild / scalaVersion := "3.8.2"
ThisBuild / organization := "io.github.smf"
ThisBuild / version := "0.1.0-SNAPSHOT"

name := "algorithms-scala"

libraryDependencies += "org.scalameta" %% "munit" % "1.1.1" % Test

testFrameworks += new TestFramework("munit.Framework")
