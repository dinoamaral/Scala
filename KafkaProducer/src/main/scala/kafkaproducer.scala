import kafka.producer.{Producer,ProducerConfig,KeyedMessage}
import java.util.Properties
import java.util.Date
import scala.util.Random
import scala.io.Source


object ProducerKafka extends App{
		  

	val command = """
	 Usage: java -jar [kafka topic to produce data] [amount of time to send]
	"""
	if (args.length < 2) println(command) 
	
	val topic = args(0)
	val events = args(1).toInt
	  
	val props = new Properties()
	props.put("metadata.broker.list","localhost:9092")
	props.put("serializer.class","kafka.serializer.StringEncoder")
	props.put("producer.type","async")
	props.put("request.required.acks","1")
	  
      println("===>>>Topic to be produced: " + topic)
          
    val config = new ProducerConfig(props)
	val producer = new Producer[String, String](config)
	val msg = Source.fromFile("{#####the complete path from file that you want to send #####}").getLines.mkString
    val data = new KeyedMessage[String, String](topic, msg);    
	  
	for(nEvents <- Range(0, events)){
	producer.send(data);
	}
}
