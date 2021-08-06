git i
name := "SBT assign"

version := "0.1"

scalaVersion := "2.13.6"

lazy val Dependencies=
  new{

    val slick = "com.typesafe.slick" %% "slick" % "3.3.3"
    val prostegrate = "org.postgresql" % "postgresql" % "42.1.1"
    val test= "com.h2database" % "h2" % "1.4.197" % Test
    val mockito="org.mockito" %% "mockito-scala" % "1.16.37" % Test
    val stest="org.scalatest" %% "scalatest" % "3.3.0-SNAP3" % Test
    val json4s="org.json4s" %% "json4s-native" % "4.0.3"
    val sljf4="org.slf4j" % "slf4j-api" % "1.7.25"
    val typesafe="com.typesafe" % "config" % "1.2.1"

  }


lazy val common = (project in file("common"))
  .settings(
    name := "common",
    libraryDependencies ++= Seq(
      Dependencies.sljf4,
      Dependencies.json4s,
      Dependencies.mockito ,
      Dependencies.stest ,
      Dependencies.typesafe
    )
  )



lazy val persistence = (project in file("persistence"))
  .settings(
    name := "persistence",
    libraryDependencies ++= Seq(
      Dependencies.slick,
      Dependencies.prostegrate,
      Dependencies.test
    )
  )

lazy val persistent = project.dependsOn(common)

lazy val root = project.in(file(".")).aggregate(common, persistence)