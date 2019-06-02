import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('statsFlow.csv', delimiter=';')
k = data['k']

flow = data['paths']

plt.plot(k, flow, label='Paths')
plt.title("Paths")
plt.legend()
plt.xlabel('K')
plt.ylabel('Paths')
#plt.yscale('log')

plt.show()