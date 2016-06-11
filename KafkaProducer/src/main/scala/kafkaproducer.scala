import kafka.producer.{Producer,ProducerConfig,KeyedMessage}
import java.util.Properties
import java.util.Date
import scala.util.Random
import scala.io.Source


object ProducerKafka extends App{
    
	  val topic = args(0)
	  val events = args(1).toInt
	  
	  val props2 = new Properties()
	  props2.put("metadata.broker.list","x64ibm033:9092,x64ibm034:9092,x64ibm035:9092,pxl1big00011:9092")
	  props2.put("serializer.class","kafka.serializer.StringEncoder")
	  props2.put("producer.type","async")
	  props2.put("request.required.acks","1")
	  
          println("===>>>Topic to be produced: " + topic)
          
          val config = new ProducerConfig(props2)
	  val producer = new Producer[String, String](config)
	  val msg = Source.fromFile("{#####the complete path from file that you want to send #####}}").getLines.mkString
          val data = new KeyedMessage[String, String](topic, msg);    
	  
	  for(nEvents <- Range(0, events)){
	  producer.send(data);
	  }
}
