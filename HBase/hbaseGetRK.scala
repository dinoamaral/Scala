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
    
    val mci = args(0)
        
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "IP_ZOOKEEPER");
    conf.set("hbase.zookeeper.property.clientPort","2181");
    conf.set("hbase.master", "IP_HBASE_MASTER" + ":60000");
    conf.set("hbase.cluster.distributed", "true");
    conf.set("hbase.rootdir", "hdfs://IP_NAMENODE:8020/hbase");

    val tableName = "sgn_vip";
    
    val table = new HTable(conf, tableName);
    
    val startRowStr = (mci+"-0").toString
    
    val endRowStr = (mci+"-9").toString
    
    val scan = new Scan(Bytes.toBytes(startRowStr), Bytes.toBytes(endRowStr))
    
    val scanner = table.getScanner(scan);
    
    while (scanner != null)
	{

	val result = scanner.next()
	if (result == null) {
	   System.exit(0);
	} else {
	val value = result.value();
        println(Bytes.toString(value));
       }
      }
    }
}



