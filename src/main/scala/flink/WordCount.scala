package flink

import java.util.Properties

import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.fs.bucketing.BucketingSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object WordCount {
  def main(args: Array[String]): Unit = {
    import org.apache.flink.streaming.api.scala._

    // get the execution environment
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("zookeeper.connect", "localhost:2181");
    properties.setProperty("group.id", "test-consumer-group");
    val stream = env.addSource(new FlinkKafkaConsumer08[String]("words", new SimpleStringSchema(), properties))

    // parse the data, group it, window it, and aggregate the counts
    val windowCounts = stream
      .flatMap { w => w.split("\\s") }
      .map { w => WordWithCount(w, 1) }
      .keyBy("word")
      .timeWindow(Time.seconds(10), Time.seconds(10))
      .sum("count")

    // print the results with a single thread, rather than in parallel
    windowCounts.map(x => println(x.word + "," + x.count))
    windowCounts.addSink(new BucketingSink[WordWithCount]("hdfs://localhost:9000/user/dpanw/demo/flinktest"))

    env.execute("Socket Window WordCount")
  }

  // Data type for words with count
  case class WordWithCount(word: String, count: Long)

}
