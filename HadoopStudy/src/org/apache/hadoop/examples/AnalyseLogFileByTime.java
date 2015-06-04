package org.apache.hadoop.examples;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.examples.LogAnalysis.IntSumReducer;
import org.apache.hadoop.examples.LogAnalysis.TokenizerMapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

	public class AnalyseLogFileByTime {
	
		public static class LogFileMapper 
	    extends Mapper<LongWritable, Text,Text, LongWritable>{
	 
	/*    private final static IntWritable one = new IntWritable(1);
	 private Text word = new Text();*/
		  LongWritable outputKey = new LongWritable(1);
	   
	 public void map(LongWritable key, Text value, Context context
	                 ) throws IOException, InterruptedException {
	  
	 	String val = value.toString();
	 	String subStr = val.substring(val.indexOf("2015-04-27"), val.indexOf("2015-04-27")+19);
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	try {
			Date date = sdf.parse(subStr);
			Date specDate = sdf.parse("2015-04-27 00:01:16");
			if(date.after(specDate)){
				System.out.println(val);
				context.write(value,outputKey);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 	
	 }
	}

	public static class LogFileReducer 
	    extends Reducer<Text, LongWritable,LongWritable,Text> {
	 private IntWritable result = new IntWritable();
	 long sum = 0L;
	 public void reduce(Text key, LongWritable values, 
	                    Context context
	                    ) throws IOException, InterruptedException {

		 context.write(new LongWritable(sum++), key);
		 System.out.println(sum);
		 

	 }
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		 conf.set("df.default.name","hdfs://master:9000/");
		 conf.set("hadoop.job.ugi","hadoop,hadoop");
		 conf.set("mapred.job.tracker","master:9001");
		 conf.set("mapred.jar", "D:/my.jar");
		 args = new String[] {"hdfs://master:9000/user/hadoop/input/ticket.log","hdfs://master:9000/user/hadoop/output/outlog1"};
		 String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		 if (otherArgs.length != 2) {
		   System.err.println("Usage: wordcount <in> <out>");
		   System.exit(2);
		 }
		 Job job = new Job(conf, "analysisLogByTime");
		 job.setJarByClass(AnalyseLogFileByTime.class);
		 job.setMapperClass(LogFileMapper.class);
		 job.setCombinerClass(LogFileReducer.class);
		 job.setReducerClass(LogFileReducer.class);
		 job.setOutputKeyClass(LongWritable.class);
		 job.setOutputValueClass(Text.class);
		 FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		 FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		 System.exit(job.waitForCompletion(true) ? 0 : 1);
	}


}
