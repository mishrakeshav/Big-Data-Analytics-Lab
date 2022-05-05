
hadoop jar hadoop-streaming-3.2.3.jar \
-file ./src/naive_map.py    -mapper "python3 naive_map.py 2 3" \
-file ./src/naive_reduce.py   -reducer "python3 naive_reduce.py 5" \
-input /matmul/test_data.txt -output /mat_mul/output