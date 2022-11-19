ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "Sfregola"
  )

val Http4sVersion = "0.22.0"
val CirceVersion = "0.14.1"
val ScalaCsvVersion = "1.3.8"
val LogbackVersion = "1.2.3"

libraryDependencies ++= List(
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-parser" % CirceVersion,
  "com.github.tototoshi" %% "scala-csv" % ScalaCsvVersion,
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
