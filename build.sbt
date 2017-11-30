name := "WordCountDemo"

version := "0.1"

scalaVersion := "2.11.5"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"

// https://mvnrepository.com/artifact/org.apache.flink/flink-core
libraryDependencies += "org.apache.flink" % "flink-core" % "1.3.2"

// https://mvnrepository.com/artifact/org.apache.flink/flink-streaming-scala
libraryDependencies += "org.apache.flink" % "flink-streaming-scala_2.11" % "1.3.2"

// https://mvnrepository.com/artifact/org.apache.flink/flink-connector-kafka
libraryDependencies += "org.apache.flink" % "flink-connector-kafka-0.8_2.11" % "1.3.2"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "org.apache.flink" %% "flink-connector-filesystem" % "1.3.2"

