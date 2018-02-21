import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.client.HTable
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.util.Bytes
import util.Properties
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.mapreduce.TableInputFormat

object HBaseSample {

  def main(args: Array[String]) {
    
    val tableName = "tab2"
    
    val conf = HBaseConfiguration.create()     

    val admin = new HBaseAdmin(conf)
    if(!admin.isTableAvailable(tableName)) {
      print("Creating " + tableName + " Table \n")
      val tableDesc = new HTableDescriptor(tableName)
      tableDesc.addFamily(new HColumnDescriptor("cf1".getBytes()));
      admin.createTable(tableDesc)
     }else{
      print("Table " + tableName + " already exists!!\n")
    }   
    
    val myTable = new HTable(conf, tableName);

    for (i <- 1 to 20) {
    val time = System.currentTimeMillis
    val p = new Put(new String("row-"+time).getBytes());
    
    p.add("cf1".getBytes(), "column-1".getBytes(), new String("value-"+time).getBytes());
    myTable.put(p);
    }
    myTable.flushCommits();
  }
}