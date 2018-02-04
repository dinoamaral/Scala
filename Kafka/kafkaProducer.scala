import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import java.util.Properties
import java.util.Date
import scala.util.Random
import scala.io.Source


object ProducerKafka{
    def main(args:Array[String]) {
	  
	    val usage = """
           =======================================================================================================================
           ===============================================Producer Kafka==========================================================
           Usage: java -jar kafkaproducer.jar <topic name> <msg> <amount of time>
           =======================================================================================================================
           """
	   
	    if (args.length != 3) 
	    { println(usage)
	     System.exit(1)
	    }
	  
	    val topic = args(0).toString
	    val msg = args(1).toString
	    val events = args(2).toInt
	  
	    println("###### Topic to be produced: " + topic)
      
        val props = new Properties()
        props.put("bootstrap.servers","cloudera-1.b2t.corp:9092")
        props.put("serializer.class","kafka.serializer.StringEncoder")
        props.put("group.id","fount.group")
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
        props.put("enable.auto.commit","true")
        props.put("auto.commit.interval.ms","5000")
        props.put("session.timeout.ms","6000")

	      val producer = new KafkaProducer[String, String](props)
        for (nEvents <- 0 until events by 1){
        val data = new ProducerRecord[String, String](topic, msg+"-"+nEvents);
        producer.send(data);
      }
        producer.close()
  }
}