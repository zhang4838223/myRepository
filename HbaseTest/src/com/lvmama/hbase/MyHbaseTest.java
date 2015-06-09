package com.lvmama.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

public class MyHbaseTest {

	public static Configuration conf = null;
	static{
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
		conf.set("hbase.zookeeper.property.cliebtPort","2181");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			HBaseJavaApi api = new HBaseJavaApi();
			api.deleteTable("worldcount");

		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void test1() throws Exception{
		HBaseJavaApi api = new HBaseJavaApi();
		String tableName = "student";
		String[] columnFamilies = new String[]{"info","course"};
		api.createTable(tableName, columnFamilies);
		
		//向表中添加第一行数据
		api.addRecord(tableName, "xiaoming", "info", "age", "20");
		api.addRecord(tableName, "xiaoming", "info", "gender", "male");
		api.addRecord(tableName, "xiaoming", "course", "math", "120");
		api.addRecord(tableName, "xiaoming", "course", "english", "128");
		api.addRecord(tableName, "xiaoming", "course", "chinese", "130");
		
		//向表中添加第二行数据
		api.addRecord(tableName, "tiger", "info", "age", "18");
		api.addRecord(tableName, "tiger", "info", "gender", "female");
		api.addRecord(tableName, "tiger", "course", "math", "110");
		api.addRecord(tableName, "tiger", "course", "english", "118");
		api.addRecord(tableName, "tiger", "course", "chinese", "135");
		
		//向表中添加第三行数据
		api.addRecord(tableName, "lion", "info", "age", "22");
		api.addRecord(tableName, "lion", "info", "gender", "male");
		api.addRecord(tableName, "lion", "course", "math", "130");
		api.addRecord(tableName, "lion", "course", "english", "108");
		api.addRecord(tableName, "lion", "course", "chinese", "145");
		
		//获取一条数据
		System.out.println("获取一条lion的数据：");
		api.getRowRecord(tableName, "lion");
		
		//获取所有数据
		System.out.println("获取所有数据：");
		api.getAllRecords(tableName);
		
		//删除一条的数据
		System.out.println("删除xiaoming的一条数据：");
		api.deleteRecord(tableName, "xiaoming");
		
		
		//删除多条数据
		System.out.println("删除多条数据：");
		api.deleteMultiRecords(tableName, new String[]{"lion","tiger"});
	}
	public void queryData(String tablename,String row,String col, String qualifier) throws Exception{
		HTable tab = new HTable(conf, tablename);
		Get get = new Get(row.getBytes());
		Result result = tab.get(get);
		byte[] value = result.getValue(col.getBytes(), qualifier.getBytes());
		System.out.println(new String(value));
	}
	
	public void putData(String tablename,String row,String col,String identifier,String value) throws Exception{
		HTable tab = new HTable(conf, tablename);
		Put put = new Put(row.getBytes());
		put.add(col.getBytes(), identifier.getBytes(), value.getBytes());
		tab.put(put);
	}
	
	public void scanAll(String tablename) throws Exception{
		
		HTable tab = new HTable(conf, tablename);
		Scan scan = new Scan();
		ResultScanner ss = tab.getScanner(scan);
		for (Result result : ss) {
			byte[] row = result.getRow();
			System.out.println(new String(row));
			KeyValue[] raw = result.raw();
			for (KeyValue kv : raw) {
				System.out.println(new String(kv.getRow())+":"+new String(kv.getFamily())+":"+new String(kv.getValue(),"utf-8"));
			}
		}
	}
	
	private static void createTable(String tablename,String col) throws IOException {
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor htd = new HTableDescriptor(tablename);
		htd.addFamily(new HColumnDescriptor(col));//这一不添加列族
		admin.createTable(htd);//这一不才会创建表
	}

}
