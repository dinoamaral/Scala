mport java.util.{Properties, Date,Random}
import java.util.concurrent.{TimeUnit,Executors}
import kafka.consumer.{Consumer,ConsumerConfig}
import kafka.producer.{Producer,ProducerConfig,KeyedMessage}
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import org.apache.log4j.{LogManager,PropertyConfigurator}

object Log {
      val logger = LogManager.getLogger(this.getClass.toString)
}

class ConsumerKafka(topic:String) {
    
    val topicCountMap = Map(topic -> 1)

    def createConsumerConfig={
	Log.logger.debug("===>>> Iniciando a coleta !!! ...")
	val props = new Properties()
	props.load(this.getClass.getResourceAsStream("consumer.properties"))
	new ConsumerConfig(props)
    }
       
    def getStreams={
        Log.logger.debug("===>>> Conectando no broker...")
        val consumer = Consumer.create(createConsumerConfig)
        val consumerMap = consumer.createMessageStreams(topicCountMap)
        consumerMap.get(topic).get(0).iterator.map(x => new String(x.message))
    }
}    

/*class JSONKafka {    
    def parseJson(line:String)={
	Log.logger.debug("===>>> Inicializado o parser do JSON ...")
        val jsonFactory = new JsonFactory
        val jp = jsonFactory.createParser(line)
        jp.nextToken// != JsonToken.START_OBJECT
        Iterator.continually(jp).takeWhile(_.nextToken != JsonToken.END_OBJECT).filter(x => x.getCurrentName != x.getText).filter(x => x.getCurrentName != "nm_sis_ogm").map(x => x.getText).mkString(";")
    }
}    
*/
/*class ProducerKafka {
    def createProducerConfig={
	val props2 = new Properties()
	Log.logger.debug("===>>> Propriedades do PRODUCER inicializadas ...")
	props2.load(this.getClass.getResourceAsStream("producer.properties"))
	new ProducerConfig(props2)
    }
}
*/
object ConsumerKafka {
    def main(args:Array[String]) {
        val log4j = new Properties()
        log4j.load(this.getClass.getResourceAsStream("log4j.properties"))
        PropertyConfigurator.configure(log4j)
        Log.logger.info("#"*50)
        Log.logger.info("Iniciando Consumer/Producer !!!")
	    println("###### Iniciando coleta <<<===")
        val topic = "spring-cloud-bus"
//       val topic2 = "sgn.taa2"
        Log.logger.info("===>>> Propriedades do CONSUMER inicializadas ...")
//       println("===>>> Consuming data from topic " + topic)
//        println("###### Transforming JSON to CSV .... ")
//       Log.logger.info("===>>> Propriedades do PRODUCER inicializadas ...")
//        println("===>>> Producing data to topic " + topic2)
        val consumerKafka = new ConsumerKafka(topic)
        val sources = consumerKafka.getStreams
//        val producerKafka = new ProducerKafka
//	    val producer = new Producer[String, String](producerKafka.createProducerConfig)
//	    val jsonKafka = new JSONKafka
//        sources.map(jsonKafka.parseJson(_)).toStream.foreach(x => producer.send(new KeyedMessage[String, String](topic2, x)))
        sources.toStream.foreach(println);
    }
}