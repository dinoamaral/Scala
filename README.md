Send a simple message and choose the amount of time to Kafka Broker.

A simple Kafka Producer written in Scala.
More details about how a Kafka Producer works may be found at: http://kafka.apache.org/documentation.html#producerapi

How to use : java -jar {name_file}.jar {topic} {amount of times}
The example bellow will send to topic "test" the file that you want 10 times, line by line.
    Ex.: java -jar kafka_producer.jar test 10