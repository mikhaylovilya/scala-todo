val scala3Version = "3.8.3"

lazy val root = project
    .in(file("."))
    .settings(
      name    := "scala-todo",
      version := "0.1.0-SNAPSHOT",

      scalaVersion := scala3Version,

      libraryDependencies += "org.scalameta" %% "munit"       % "1.3.0" % Test,
      libraryDependencies += "org.typelevel" %% "cats-effect" % "3.7.0",
      libraryDependencies += "org.tpolecat"  %% "doobie-core" % "1.0.0-RC8",
      libraryDependencies += "org.tpolecat" %% "doobie-hikari"   % "1.0.0-RC8",
      libraryDependencies += "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC8",
      libraryDependencies += "org.tpolecat" %% "doobie-h2"       % "1.0.0-RC8"
    )
