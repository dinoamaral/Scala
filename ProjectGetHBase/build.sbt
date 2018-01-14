name := "get-HBase"

version := "1.1"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.kafka" %% "kafka" % "0.8.2.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.6.3"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.4" excludeAll(
    ExclusionRule(organization = "commons-beanutils"),
    ExclusionRule(organization = "org.apache.hadoop", name = "hadoop-yarn-common")
)

libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.1.2"

libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.1.2"