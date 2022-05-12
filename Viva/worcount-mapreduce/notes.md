
hadoop jar hadoop-streaming-3.2.3.jar -file mapper.py  -mapper "python mapper.py" -file reducer.py   - reducer "python  reducer.py" -input /wordcount/input.txt -output /wordcount/output.txt

hadoop fs -ls /hm/output/
hadoop fs -cat /hm/output/part-0-Success