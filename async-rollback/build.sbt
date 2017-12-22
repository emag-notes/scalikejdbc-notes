
name := """async-rollback"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-Xfatal-warnings",
  "-language:_",
  "-Ywarn-adapted-args",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-numeric-widen"
)

val scalikeJdbcVersion = "3.1.0"
val scalikejdbcPlayVersion = "2.6.0-scalikejdbc-3.1"
val silhouetteVersion = "5.0.2"

libraryDependencies ++= Seq(
  guice,
  "org.scalikejdbc" %% "scalikejdbc" % scalikeJdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-config" % scalikeJdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % scalikejdbcPlayVersion,
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % scalikejdbcPlayVersion,
  "mysql" % "mysql-connector-java" % "5.1.44",
  "org.flywaydb" %% "flyway-play" % "4.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.scalikejdbc" %% "scalikejdbc-test" % scalikeJdbcVersion % Test,
)

import com.typesafe.config.{Config, ConfigFactory}

lazy val envConfig = settingKey[Config]("env-config")
envConfig := {
  ConfigFactory.parseFile(file("conf") / s"application.conf")
}

flywayLocations := List("filesystem:conf/db/migration/default")
flywayDriver := "com.mysql.jdbc.Driver"
flywayUrl := envConfig.value.getString("db.default.url")
flywayUser := "test"
flywayPassword := "test"