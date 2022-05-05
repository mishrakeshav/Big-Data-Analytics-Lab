
hadoop jar hadoop-streaming-3.2.3.jar \
-file mapper.py    -mapper mapper.py \
-file reducer.py   -reducer reducer.py \
-input /wordcount/input.txt -output /wordcount/output.txt