import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('k1.csv', delimiter=';')
plots = ['comparations', 'swaps', 'time']
x = data['n']

s = data['select - ' + plots[0]]
i = data['insert - ' + plots[0]]
q = data['quick - ' + plots[0]]
m = data['mquick - ' + plots[0]]
h = data['heap - ' + plots[0]]

plt.plot(x, s, label='select')
plt.plot(x, i, label='insert')
plt.plot(x, q, label='quick')
plt.plot(x, m, label='mquick')
plt.plot(x, h, label='heap')
plt.legend()
plt.title(plots[0])

plt.show()


s = data['select - ' + plots[1]]
i = data['insert - ' + plots[1]]
q = data['quick - ' + plots[1]]
m = data['mquick - ' + plots[1]]
h = data['heap - ' + plots[1]]

plt.plot(x, s, label='select')
plt.plot(x, i, label='insert')
plt.plot(x, q, label='quick')
plt.plot(x, m, label='mquick')
plt.plot(x, h, label='heap')
plt.legend()
plt.title(plots[1])

plt.show()


s = data['select - ' + plots[2]]
i = data['insert - ' + plots[2]]
q = data['quick - ' + plots[2]]
m = data['mquick - ' + plots[2]]
h = data['heap - ' + plots[2]]

plt.plot(x, s, label='select')
plt.plot(x, i, label='insert')
plt.plot(x, q, label='quick')
plt.plot(x, m, label='mquick')
plt.plot(x, h, label='heap')
plt.legend()
plt.title(plots[2])

plt.show()
