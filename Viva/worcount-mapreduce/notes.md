
hadoop jar hadoop-streaming-3.2.3.jar \
-file ./mapper.py    -mapper "python3 mapper.py" \
-file ./reducer.py   -reducer "python3 reducer.py" \
-input /wordcount/input.txt -output /wordcount/output22.txt