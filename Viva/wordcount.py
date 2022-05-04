from collections import Counter
file = open("sample.txt").read()
file = file.split()
freq = Counter(file)
print(freq)