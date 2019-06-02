import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('statsFlow.csv', delimiter=';')
k = data['k']

flow = data['flow']

plt.plot(k, flow, label='flow')
plt.title("Flows")
plt.legend()
#plt.yscale('log')

plt.show()