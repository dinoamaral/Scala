name := "Kafka"

version := "1.1"

scalaVersion := "2.11.1"

//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1" % "provided"

//libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "1.0.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.6.3"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.3"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.3"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }