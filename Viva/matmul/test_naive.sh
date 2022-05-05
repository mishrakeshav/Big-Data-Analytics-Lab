#!/bin/bash

module load python/gnu/3.4.4

cat data/test_data.txt | python3 src/naive_map.py 2 3 | sort -n | python3 src/naive_reduce.py 5


hadoop jar hadoop-streaming-3.2.3.jar \
-file ./mapper.py    -mapper "python3 mapper.py" \
-file ./reducer.py   -reducer "python3 reducer.py" \
-input /wordcount/input.txt -output /wordcount/output22.txt