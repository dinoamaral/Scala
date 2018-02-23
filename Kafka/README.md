## Here follows our tasks :
### The Log: What every software engineer should know about real-time data's unifying abstraction
In the link bellow, Jay Kreps explains in a philosophical approach why he and his team built Apache Kafka at LinkedIn:
* https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying

### Benchmarking Apache Kafka: 2 Million Writes Per Second (On Three Cheap Machines)
* https://engineering.linkedin.com/kafka/benchmarking-apache-kafka-2-million-writes-second-three-cheap-machines

### Kafka Introduction
In the link below, there is a simple introduction about Apache Kafka. It is necessary to read it first, to get a brief notion what Apache Kafka may help us in the futures projects : 
* http://kafka.apache.org/intro

### The first steps with Apache kafka :
* http://kafka.apache.org/quickstart 

### A Kafka Producer
* In the following link, there is a simple code for a Kafka Producer :
 https://github.com/dinoamaral/Scala/blob/master/Kafka

* For a deeper details about Kafka Producer API, see the link bellow :
https://kafka.apache.org/0100/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html

### A Kafka Consumer
* In the following link, there is a simple code for a Kafka Producer :
 https://github.com/dinoamaral/Scala/blob/master/Kafka

* For a deeper details about Kafka Producer API, see the link bellow :
https://kafka.apache.org/0100/javadoc/index.html?org/apache/kafka/clients/consumer/KafkaConsumer.html

### RealTime Interaction

* In the folder RealTime_Interaction, there is some lines of codes that allows a birectional interaction with 2 distincts Kafka Topics.
In the file RealTime_Interaction/src/main/scala/RT_Interaction.scala has the core of target, which is a very simple action. The consumer receives the data from one topic, adda a simple string (PROCESSADO) to this line and the producer sends this data to a new topic.

In the file RealTime_Interaction/src/main/scala/ConsProd.scala, a code for Kafka Producer and Consumer are available calling external files with their configuration. Producer (RealTime_Interaction//src/main/resources/producer.properties) and Consumer(RealTime_Interaction/src/main/resources/consumer.properties) 

 