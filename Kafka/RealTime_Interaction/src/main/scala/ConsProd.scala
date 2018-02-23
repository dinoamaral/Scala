import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.log4j.LogManager

import scala.collection.Iterator
import scala.collection.JavaConverters._

object Log {
  val logger = LogManager.getLogger(this.getClass.toString)
}

class ConsProd {

  def kafkaConsumer(topics: String) = {
    val props = new Properties()
    Log.logger.debug("===>>> Conectando no broker...")
    props.load(this.getClass.getResourceAsStream("consumer.properties"))
    val consumer = new KafkaConsumer[String, String](props)
    //consumer.subscribe(util.Arrays.asList("sgn.aapj"))
    consumer.subscribe(util.Arrays.asList("big.taa"))
    Iterator.continually(consumer.poll(Long.MaxValue).asScala.map(_.value)).flatten
  }

  def kafkaProducerConfig = {
    val props2 = new Properties()
    Log.logger.debug("===>>> Propriedades do PRODUCER inicializadas ...")
    props2.load(this.getClass.getResourceAsStream("producer.properties"))
    props2
  }
}




