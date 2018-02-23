import java.util.{Calendar, Date, Properties}
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.{LogManager, PropertyConfigurator}


object RT_Interaction {

  def main(args:Array[String]) {
    val log4j = new Properties()
    log4j.load(this.getClass.getResourceAsStream("log4j.properties"))
    PropertyConfigurator.configure(log4j)
    Log.logger.info("#"*50)
    Log.logger.info("===>>> Propriedades do CONSUMER inicializadas")
    Log.logger.info("===>>> Iniciando a Coleta")
    val topic = "b2t.in"
    val topic2 = "b2t.out"
    Log.logger.info("===>>> Consuming data from topic " +'"'+ topic +'"')
    Log.logger.info("===>>> Transforming JSON to CSV")
    Log.logger.info("===>>> Showing Consumer properties")
    Log.logger.info("#"*50)
    val kafkaMsg = new ConsProd

    val consumerKafka = kafkaMsg.kafkaConsumer(topic)
    val producerKafkaConf = kafkaMsg.kafkaProducerConfig
    val producer = new KafkaProducer[String,String](producerKafkaConf)

    consumerKafka.foreach{x =>
		val msg2 =  s""",PROCESSADO"""
		//println(json_parsed)
		val msg = x.init + msg2
		producer.send(new ProducerRecord[String, String](topic2, msg))}
  }
}
