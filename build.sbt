import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "di.look.gatling",
      scalaVersion := "2.13.8",
      version := "0.1.0-SNAPSHOT",
      scalacOptions := Seq("-encoding", "utf8")
    )),
    name := "gatling-load",
    libraryDependencies ++= gatling,
    libraryDependencies ++= gatlingPicatinny
  )
