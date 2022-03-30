import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.hadoop.mapreduce.Reducer;

public class knn
{	
	public static class REDUCE extends Reducer<Text, Text, Text, Text>
{
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		String str=null;
		int maxvalue=-1;
		for(Text value:values)
		{
			if(!map.containsKey(value.toString()))
			{
				map.put(value.toString(),1);
				System.out.println(value.toString());
			}
			else
			{
				map.put(value.toString(),map.get(value.toString()+1));
				System.out.println(value.toString());
			}
		}
		System.out.println(map.size());
		Iterator it=map.entrySet().iterator();
		str=((Entry)it.next()).getKey().toString();
		while(it.hasNext())
		{
			Entry entry=(Entry)it.next();
			if(Integer.parseInt(entry.getValue().toString())>maxvalue)
			{
				str=entry.getKey().toString();
				maxvalue=Integer.parseInt(entry.getValue().toString());
			}
		}
		context.write(null,new Text("Class label : "+str));
	}
}

	public static class MAP extends Mapper<LongWritable, Text, Text, Text>
{
	public static int numoffeatures;
	public static Float[] feature;
	public static ArrayList<String> dist=new ArrayList<String>();
	public static float euclideandist(Float[] test,int n)
	{
		float distance=0;
		for(int j=0;j<n;j++)
		{
			distance+=Math.pow((feature[j]-test[j]),2);
		}
		distance=(float)Math.sqrt(distance);
		return distance;
	}
	public void setup(Context context) throws IOException, InterruptedException
	{
		numoffeatures=context.getConfiguration().getInt("numoffeatures",1);
		feature=new Float[numoffeatures];
		for(int j=0;j<numoffeatures;j++)
		{
			feature[j]=context.getConfiguration().getFloat("feature"+j,0);
		}
	}
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String[] input=value.toString().split("\\ ");
		Float[] test=new Float[numoffeatures];
		for(int j=0;j<numoffeatures;j++)
		{
			test[j]=Float.parseFloat(input[j]);
		}
		String classlabel=input[numoffeatures].replace("\"","");
		dist.add(String.valueOf(euclideandist(test,numoffeatures))+classlabel);
	}
	public void cleanup(Context context) throws IOException, InterruptedException
	{
		Collections.sort(dist);
		String[] label=new String[5];
		String temp;
		for(int j=0;j<5;j++)
		{
			temp=dist.get(j);
			label[j]=String.valueOf(temp.replaceAll("[\\d.]",""));
			context.write(new Text("1"),new Text(label[j]));
		}
	}
}
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{
		System.out.println("Hello World!\n");
		Configuration conf=new Configuration();
		FileSystem hdfs=FileSystem.get(conf);
		BufferedReader br=new BufferedReader(new InputStreamReader(hdfs.open(new Path(args[0]))));
		String line=null;
		int numoffeatures=0;
		while((line=br.readLine())!=null)
		{
			String[] feature=line.split("\\ ");
			numoffeatures=feature.length;
			for(int j=0;j<numoffeatures;j++)
				conf.setFloat("feature"+j,Float.parseFloat(feature[j]));
		}
		br.close();
		hdfs.close();
		conf.setInt("numoffeatures",numoffeatures);
		Job job=new Job(conf,"KNN Classification");
		job.setJarByClass(knn.class);
		FileInputFormat.setInputPaths(job,new Path(args[1]));
		FileOutputFormat.setOutputPath(job,new Path(args[2]));
		job.setMapperClass(MAP.class);
		job.setReducerClass(REDUCE.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.waitForCompletion(true);
	}
}
