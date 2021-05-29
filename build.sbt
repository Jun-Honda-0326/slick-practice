name := """slick-example"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
lazy val slickCodeGen = taskKey[Unit]("excute Slick Codegen")
slickCodeGen          := (runMain in Compile).toTask(" tasks.SlickCodeGen").value

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  guice,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  // slickを導入
  "com.typesafe.play"      %% "play-slick"            % "5.0.0",
  "com.typesafe.play"      %% "play-slick-evolutions" % "5.0.0",
  "com.typesafe.slick"     %% "slick-codegen"         % "3.3.2",
  "mysql"                  % "mysql-connector-java"   % "6.0.6",
  "com.typesafe"           % "config"                 % "1.4.0"
)
