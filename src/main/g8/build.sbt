import akka.sbt._

organization := "$package$"

name := "$name$"

resolvers ++= Seq(
  "ScalaTools Snapshots nexus" at "http://nexus.scala-tools.org/content/repositories/snapshots",
  "Akka Repository" at "http://akka.io/repository",
  "Scala Tools" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"                 % "akka-kernel"       % "$akka_version$",
  "org.scalaz"                       %% "scalaz-core"       % "$scalaz_version$",
  "org.specs2"                       %% "specs2"            % "$specs2_version$"             % "test",
  "org.scala-tools.testing"          %% "scalacheck"        % "1.9"               % "test",
  "junit"                             % "junit"             % "4.10"              % "test"
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
