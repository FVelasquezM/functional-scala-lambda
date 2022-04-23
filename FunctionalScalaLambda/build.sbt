import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

val awsLambdaVersion = "1.2.1"
val awsLambdaEventsVersion = "3.11.0"

lazy val root = (project in file("."))
  .settings(
    name := "functional-scala-lambda",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % awsLambdaVersion,
      "com.amazonaws" % "aws-lambda-java-events" % awsLambdaEventsVersion,
      scalaTest % Test
    )
  )

assembly / assemblyOutputPath := file("target/lambdaFunction.jar")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
