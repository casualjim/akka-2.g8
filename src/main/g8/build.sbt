import scalariform.formatter.preferences._
import akka.sbt._

organization := "$package$"

name := "$name$"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "Akka Repository" at "http://akka.io/repository",
  "Scala Tools" at "https://oss.sonatype.org/content/repositories/snapshots"
)

autoCompilerPlugins := true

libraryDependencies ++= Seq(
  compilerPlugin("org.scala-lang.plugins" % "continuations" % "2.9.1"),
  "com.typesafe.akka"                 % "akka-kernel"       % "$akka_version$",
  "org.scalaz"                       %% "scalaz-core"       % "$scalaz_version$",
  "com.typesafe.akka"                 % "akka-testkit"       % "$akka_version$" % "test",
  "org.specs2"                       %% "specs2"            % "$specs2_version$"   % "test",
  "org.scala-tools.testing"          %% "scalacheck"        % "1.9"    % "test",
  "junit"                             % "junit"             % "4.10"   % "test"
)

ivyXML := <dependencies>
    <exclude module="log4j" />
    <exclude module="slf4j-log4j12" />
    <exclude module="slf4j-api-1.6.0"  />
    <exclude org="org.jboss.netty" />
  </dependencies>

seq(AkkaKernelPlugin.distSettings :_*)

testOptions in Test += Tests.Setup( () => System.setProperty("akka.mode", "test") )

testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "console", "junitxml")

testOptions in Test <+= (crossTarget map { ct =>
 Tests.Setup { () => System.setProperty("specs2.junit.outDir", new File(ct, "specs-reports").getAbsolutePath) }
})

javacOptions ++= Seq("-Xlint:unchecked", "-source", "1.7", "-Xlint:deprecation")

scalacOptions ++= Seq("-optimize", "-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8", "-P:continuations:enable")

seq(scalariformSettings: _*)

ScalariformKeys.preferences :=
  (FormattingPreferences()
        setPreference(IndentSpaces, 2)
        setPreference(AlignParameters, false)
        setPreference(AlignSingleLineCaseStatements, true)
        setPreference(DoubleIndentClassDeclaration, true)
        setPreference(RewriteArrowSymbols, true)
        setPreference(PreserveSpaceBeforeArguments, true)
        setPreference(IndentWithTabs, false))

(excludeFilter in ScalariformKeys.format) <<= (excludeFilter) (_ || "*Spec.scala")

