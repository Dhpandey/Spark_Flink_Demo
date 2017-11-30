package spark

import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      throw new Exception("Enter input and output location")
    }
    val inputLocation = args(0);
    val outputLocation = args(1)

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("WordCountDemo")
      .getOrCreate()

    val input = sparkSession.sparkContext.textFile("hdfs://localhost:9000/" + inputLocation)

    val result = input.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    result.saveAsTextFile("hdfs://localhost:9000/" + outputLocation)

  }
}
