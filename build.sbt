name := "playpingpong"

version := "1.0"

lazy val `playpingpong` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
//  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.2.0",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  cache, ws,
  specs2 % Test )


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"


//
//// required
//slickCodegenSettings
//
//// required
//// Register codegen hook
//sourceGenerators in Compile <+= slickCodegen
//
//// required
//slickCodegenDatabaseUrl := "jdbc:mysql://localhost/lsmember_development"
//
//// required
//slickCodegenDatabaseUser := "root"
//
//// required
//slickCodegenDatabasePassword := ""
//
//// required (If not set, postgresql driver is choosen)
//slickCodegenDriver := slick.driver.MySQLDriver
//
//// required (If not set, postgresql driver is choosen)
//slickCodegenJdbcDriver := "com.mysql.jdbc.Driver"
//
//// optional but maybe you want
//slickCodegenOutputPackage := "models.rails"
//
//// optional, pass your own custom source code generator
//slickCodegenCodeGenerator := { (model: m.Model) => new SourceCodeGenerator(model) }
//
//// optional
//// For example, to exclude flyway's schema_version table from the target of codegen
//slickCodegenExcludedTables in Compile := Seq("schema_version")
//
////optional
//slickCodegenOutputDir := (sourceManaged in Compile).value