hadoop jar contrib/streaming/hadoop-*streaming*.jar \
-file mapper.py    -mapper mapper.py \
-file reducer.py   -reducer reducer.py \
-input wordcount/input.txt -output /wordcount/output.txt