import Dependencies._

lazy val v2_12 = "2.12.14"
lazy val v2_13 = "2.13.6"
lazy val v3_0  = "3.0.1"

crossScalaVersions := Seq(v3_0, v2_13, v2_12)
scalaVersion       := crossScalaVersions.value.head
organization       := "io.github.akiomik"
homepage           := Some(url("https://github.com/akiomik/seaw"))
scmInfo            := Some(
  ScmInfo(
    url("https://github.com/akiomik/seaw"),
    "git@github.com:akiomik/seaw.git"
  )
)
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
developers         := List(
  Developer(
    id = "akiomik",
    name = "Akiomi Kamakura",
    email = "akiomik@gmail.com",
    url = url("https://github.com/akiomik")
  )
)

// for scalafix
semanticdbEnabled := true
semanticdbVersion := scalafixSemanticdb.revision

lazy val scala2scalacOptions = Seq("-Xlint", "-Ywarn-dead-code", "-Ywarn-numeric-widen", "-Ywarn-value-discard", "-Ywarn-unused")
lazy val scala3scalacOptions = Seq.empty

lazy val root = project
  .in(file("."))
  .settings(
    name    := "seaw",
    version := "0.1.0",
    libraryDependencies ++= Seq(
      icu4j,
      scalaTest % Test
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions"
    ) ++ (if (scalaVersion.value.startsWith("3.0")) scala3scalacOptions else scala2scalacOptions)
  )
