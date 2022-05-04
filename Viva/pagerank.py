import networkx as nx
G=nx.barabasi_albert_graph(60,2)
pr=nx.pagerank(G,0.4)
pr
nx.draw(G,with_labels=True)
