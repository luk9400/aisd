import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('statsFlow.csv', delimiter=';')
k = data['k']

flow = data['time']

plt.plot(k, flow, label='time')
plt.title('Times')
plt.legend()
plt.ylabel('Time [s]')
plt.xlabel('K')
#plt.yscale('log')

plt.show()