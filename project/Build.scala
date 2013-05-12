import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Resolvers {

  val myResolvers = Seq(
    "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
    "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    "Twitter Maven" at "http://maven.twttr.com"
  )

}

object BuildSettings {

  import Dependencies._
  import Resolvers._

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "pl.project13.geecon",
    name := "agendaparser",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.9.2",

    resolvers := myResolvers,
    scalacOptions += "-unchecked",
    libraryDependencies ++= Seq(jsoup, json4sJackson)
  )

}

object Dependencies {
  val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.2.4"

  val words   = "pl.project13.scala" %% "words"   % "0.2"

  val jsoup = "org.jsoup" % "jsoup" % "1.7.1"

}

object MeetupGetNamesBuild extends Build {

  import BuildSettings._

  lazy val parent: Project = Project(
    "root",
    file("."),
    settings = buildSettings ++ assemblySettings
  )

}
