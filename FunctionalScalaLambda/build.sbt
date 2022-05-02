import Dependencies._

ThisBuild / scalaVersion := "3.1.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.fvelasquezm"
ThisBuild / organizationName := "fvelasquezm"

val awsLambdaVersion = "1.2.1"
val awsLambdaEventsVersion = "3.11.0"

lazy val root = (project in file("."))
  .settings(
    name := "functional-scala-lambda",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % awsLambdaVersion,
      "com.amazonaws" % "aws-lambda-java-events" % awsLambdaEventsVersion,
      "org.typelevel" %% "cats-core" % "2.7.0",
      "org.typelevel" %% "cats-effect" % "3.3.11",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
      "software.amazon.awssdk" % "dynamodb" % "2.17.165",
      scalaTest % Test
    )
  )

assembly / assemblyOutputPath := file("target/lambdaFunction.jar")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.