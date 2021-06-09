val projectName = "drunk"

name := projectName

organization := "ch.linkyard.fork"

crossScalaVersions := Seq("2.13.6")

scalaVersion := crossScalaVersions.value.head

organizationName := "linkyard"
startYear := Some(2021)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

credentials += Credentials(Path.userHome / ".sbt" / ".linkyard-credentials")
publishTo := {
  val nexus = "https://nexus.linkyard.ch/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "repository/maven-snapshots/")
  else
    Some("releases"  at nexus + "repository/maven2-public/")
}

Global / onChangedBuildSource := ReloadOnSourceChanges

// ··· Project Options ···

lazy val root = (project in file("."))

scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-feature",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-unchecked",
  "-deprecation",
  "-Ymacro-expand:normal"
)

// ··· Project Repositories ···

resolvers ++= Seq(Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots"))

// ··· Project Dependencies ···
val sangriaV        = "2.1.3"
val sangriaCirceV   = "1.3.2"
val akkaV           = "2.6.14"
val akkaHttpV       = "10.2.4"
val akkaHttpCircleV = "1.36.0"
val circeV          = "0.14.1"
val slf4JV          = "1.7.30"
val logbackV        = "1.2.3"
val scalatestV      = "3.2.9"

libraryDependencies ++= Seq(
  // --- GraphQL --
  "org.sangria-graphql" %% "sangria"          % sangriaV,
  "org.sangria-graphql" %% "sangria-circe"    % sangriaCirceV,
  // --- Akka --
  "com.typesafe.akka"   %% "akka-http"        % akkaHttpV,
  "com.typesafe.akka"   %% "akka-stream"      % akkaV,
  "de.heikoseeberger"   %% "akka-http-circe"  % akkaHttpCircleV,
  // --- Utils ---
  "io.circe"            %% "circe-generic"    % circeV,
  "io.circe"            %% "circe-parser"     % circeV,
  // --- Logger ---
  "org.slf4j"           %  "slf4j-api"        % slf4JV,
  "ch.qos.logback"      %  "logback-classic"  % logbackV        % Test,
  // --- Testing ---
  "com.typesafe.akka"   %% "akka-testkit"       % akkaV         % Test,
  "com.typesafe.akka"   %% "akka-http-testkit"  % akkaHttpV     % Test,
  "org.scalatest"       %% "scalatest"          % scalatestV    % Test
)


// ··· Testing Configuration ···

fork in (Test, run) := false

scalacOptions in Test ++= Seq("-Yrangepos")
