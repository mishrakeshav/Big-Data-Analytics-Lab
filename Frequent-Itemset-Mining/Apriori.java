import java.util.*;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;

import org.apache.hadoop.*;

public class Project {
	static int ntrans=0;

  public static class MapperClass 
       extends Mapper<Object, Text, Text, IntWritable>{
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
   
      
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer items = new StringTokenizer(value.toString()," \t\n\r\f,.:;?![]'");
      LinkedList <String>list = new LinkedList<String>();
      LinkedList <String>clist = new LinkedList<String>();
      LinkedList <String>templist1 = new LinkedList<String>();
      LinkedList <String>templist2 = new LinkedList<String>();
	ntrans++;
	String str="";
	int f=0;
	int count=0,i=0,j=0,nitem=0;
     Iterator iterator;

	while (items.hasMoreTokens()) {
        word.set(items.nextToken());
	nitem++;
	count=0;
        context.write(word, one);
      }
     StringTokenizer items2 = new StringTokenizer(value.toString()," \t\n\r\f,.:;?![]'");
	while (items2.hasMoreTokens()) {
        list.add(items2.nextToken());
      }

	count=0;
	clist.clear();
	
		count=1;

	while (count < nitem)
	{
		count=count+1;

		for (i=0;i<(list.size()-1);i++)
		{
			
			items2 = new StringTokenizer(list.get(i));
			while (items2.hasMoreTokens())
			{       
				templist1.add(items2.nextToken());
			}

			for (j=i+1;j<list.size();j++)
			{
				items2 = new StringTokenizer(list.get(j));
				while (items2.hasMoreTokens())
				{      
					templist2.add(items2.nextToken());
				}

				f=0;
				for (int k=0;k < (templist1.size()-1);k++)
				{
					if(!(templist1.get(k).equals(templist2.get(k))))
					{
						f=1;
						break;
					}
				}

				if(f == 0)
				{
					str="";
					str=list.get(i)+" "+templist2.get(templist2.size()-1);

					clist.add(str);
					items2 = new StringTokenizer(str,"\n");
					if(items2.hasMoreTokens()){
					word.set(items2.nextToken());
					context.write(word, one);
					}
				}
				templist2.clear();
			}
	  			
			templist1.clear();
		}
		list.clear();
		for(int k=0;k < clist.size();k++)
		list.add(clist.get(k));
		clist.clear();
	}	
    }
  }
  public static class ReducerClass
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, 
  

                     Context context) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
	
      result.set(sum);
	if(sum>7)
      context.write(key, result);
    } 
  }

 public static void main(String[] args) throws Exception {

	long start = System.currentTimeMillis( );
	int m;
	System.out.println("Enter one inteser");
	Scanner in=new Scanner(System.in);	

    Configuration conf = new Configuration();
    Job job = new Job(conf, "project");
    job.setJarByClass(Project.class);
    job.setMapperClass(MapperClass.class);
    job.setReducerClass(ReducerClass.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	if(job.waitForCompletion(true))
	{
		
	}
	else
	System.exit(1);
  }
}
