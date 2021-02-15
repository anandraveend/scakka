enablePlugins(JavaAppPackaging)

import sbt._

name:= "scala-http"

version:="1.0"

scalaVersion:= "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.11.7",
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "org.json4s" %% "json4s-native" % "3.2.11",
  "org.json4s" %% "json4s-ext" % "3.2.11",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "io.spray" % "spray-http_2.11" % "1.3.1",
  "io.spray" % "spray-routing_2.11" % "1.3.1",
  "io.spray" % "spray-can_2.11" % "1.3.1",
  "commons-io" % "commons-io" % "2.5",
  "org.log4s" %% "log4s" %"1.2.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.3",
  "org.apache.logging.log4j" % "log4j" % "2.14.0" pomOnly(),
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "com.github.swagger-akka-http" % "swagger-akka-http_2.11" % "0.14.0"
)
// https://mvnrepository.com/artifact/com.wordnik/swagger-annotations
libraryDependencies += "com.wordnik" % "swagger-annotations" % "1.3.13"
// https://mvnrepository.com/artifact/com.wordnik/swagger-core
libraryDependencies += "com.wordnik" %% "swagger-core" % "1.3.12"
// https://mvnrepository.com/artifact/com.wordnik/common-utils
libraryDependencies += "com.wordnik" %% "common-utils" % "1.3.0"
// https://mvnrepository.com/artifact/javax.ws.rs/javax.ws.rs
libraryDependencies += "javax.ws.rs" % "javax.ws.rs-api" % "2.0"
// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.1"
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
// https://mvnrepository.com/artifact/com.gettyimages/spray-swagger
libraryDependencies += "com.gettyimages" %% "spray-swagger" % "0.5.1"



