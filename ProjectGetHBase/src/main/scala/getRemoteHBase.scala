import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import scala.util.control._

object HBaseKafka {
  def main (args:Array[String]){
     
    if (args.length == 0 || args.length >= 3) {
      System.err.println("=======================================================================================================================")
      System.err.println("Consulta na base do VIP oriundo do SGN-ARES")   
      System.err.println("Usage: java -jar get-HBase.jar <agencia> <conta> | java -jar get-HBase-assembly-1.1.jar <numero do cartao>")
      System.err.println("=======================================================================================================================")
      System.exit(1)
    }
    
    val rowFilter = 
	if (args.length == 1) {
        val cartao = args(0)
        new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(cartao)) 
	} else {
        val agencia = args(0)
        val conta = args(1)
        new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(agencia+"-"+conta))
        }
    
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "172.17.152.11");
    conf.set("hbase.zookeeper.property.clientPort","2181");
    conf.set("hbase.master", "172.17.152.11" + ":60000");
    conf.set("hbase.cluster.distributed", "true");
    conf.set("hbase.rootdir", "hdfs://172.17.152.11:8020/hbase");
    
    val tableName = "vip_kafka";
    
    val table = new HTable(conf, tableName);
    
    val scan = new Scan();
        
    scan.setFilter(rowFilter);
    
    val scanner = table.getScanner(scan);
    
    while (scanner != null)
	{

	val result = scanner.next()//value().getBytes.toString.foreach(println)
//	for (result <- 0)
	if (result == null) {
	   System.exit(0);
	  } else {
	   val value = result.value();
     println(Bytes.toString(value));
      }
    }
  }
}