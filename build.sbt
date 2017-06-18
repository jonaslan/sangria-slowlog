name := "sangria-slowlog"
organization := "org.sangria-graphql"
version := "0.1.0-SNAPSHOT"

description := "Sangria middleware to log slow GraphQL queries"
homepage := Some(url("http://sangria-graphql.org"))
licenses := Seq("Apache License, ASL Version 2.0" → url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.12.2"
crossScalaVersions := Seq("2.11.11", "2.12.2")

scalacOptions ++= Seq("-deprecation", "-feature")

scalacOptions ++= {
  if (scalaVersion.value startsWith "2.12")
    Seq.empty
  else
    Seq("-target:jvm-1.7")
}

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria" % "1.2.2",
  "io.dropwizard.metrics" % "metrics-core" % "3.2.2",
  "org.slf4j" % "slf4j-api" % "1.7.25",

  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "org.sangria-graphql" %% "sangria-json4s-native" % "1.0.0" % Test,
  "org.slf4j" % "slf4j-simple" % "1.7.25" % Test
)

git.remoteRepo := "git@github.com:sangria-graphql/sangria-slowlog.git"

// Publishing

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ ⇒ false)
publishTo := Some(
  if (version.value.trim.endsWith("SNAPSHOT"))
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

// Site and docs

site.settings
site.includeScaladoc()
ghpages.settings

// nice *magenta* prompt!

shellPrompt in ThisBuild := { state ⇒
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

// Additional meta-info

startYear := Some(2017)
organizationHomepage := Some(url("https://github.com/sangria-graphql"))
developers := Developer("OlegIlyenko", "Oleg Ilyenko", "", url("https://github.com/OlegIlyenko")) :: Nil
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/sangria-graphql/sangria-circe.git"),
  connection = "scm:git:git@github.com:sangria-graphql/sangria-circe.git"
))