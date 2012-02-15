resolvers ++= Seq(
  "Akka Repository" at "http://akka.io/repository",
  Classpaths.typesafeResolver
)


addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.3.1")

addSbtPlugin("com.typesafe.akka" % "akka-sbt-plugin" % "$akka_version$")