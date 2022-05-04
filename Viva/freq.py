#Apriori
import numpy as np
import pandas as pd
from apyori import apriori
store_data=pd.read_csv('dataset_apriori.csv',header=None)
print(store_data)
records=[]
for i in range(0,22):
    records.append([str(store_data.values[i,j]) for j in range(0,6)])
print(records)
association_rules = apriori(records,min_support=0.5,min_confidence=0.7,min_lift=1.2,min_length=2)
association_results=list(association_rules)
print(association_results)