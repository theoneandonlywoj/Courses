import numpy as np
import matplotlib.pyplot as plt

class One_Armed_Bandit:
	def __init__(self, true_mean, optimistic_initial):
		# Assigning the true mean as m
		self.true_mean = true_mean 
		# Initializing mean as optimistic value of 10.
		# It will be further updated in the iteration
		self.mean = optimistic_initial
		# Intializing number of actions to zero
		self.N = 0

	def action(self):
		# It will return a true mean + a noise.
		return np.random.randn() + self.true_mean

	def update(self, x):
		# Incrementation of the number counter since the action was performed
		self.N +=1
		# Updating mean according to efficient calculation 
		# of average of a certain group of numbers.
		self.mean = (1.0 - 1.0 / self.N) * self.mean + 1.0 / self.N * x

def perform_experiment(m1, m2, m3, optimistic_initial, N):
	# Creating an array of bandits
	bandits = [One_Armed_Bandit(m1, optimistic_initial), One_Armed_Bandit(m2, optimistic_initial), One_Armed_Bandit(m3, optimistic_initial)]

	# Initializing a variable data as empty. 
	# It will be filled further in the process.
	data = np.empty(N)

	# N - number of pulls of the bandits arm
	for i in range(N):
		# With the optimistic initial values, we eliminate the greedy epsilon part
		# Choosing a bandit with the highest mean = Picking up the best one!
		choice = np.argmax([b.mean for b in bandits])

		# Performing an action for the chosen bandit
		x = bandits[choice].action()
		# Updating the value of mean = response from the environment
		bandits[choice].update(x)

		# Recording the learning process
		data[i] = x

	# Cumulative average
	# np. arange(3) = [0, 1, 2]
	# That is why wee need to add one to 
	# or np.arange(1, N + 1)
	cumulative_average = np.cumsum(data) / (np.arange(N) + 1)

	# Plotting results
	plt.plot(cumulative_average)
	plt.plot(np.ones(N) * m1)
	plt.plot(np.ones(N) * m2)
	plt.plot(np.ones(N) * m3)
	# Choosing logarithmic scale for better visual representation
	plt.xscale('log')

	for b in bandits:
		print('Mean for bandit', b, ':', b.mean)

	return cumulative_average


if __name__ == '__main__':
	Experiment1 = perform_experiment(1.0, 2.0, 3.0, 100 ,10000)
	Experiment2 = perform_experiment(1.0, 2.0, 3.0, 10, 10000)
	Experiment3 = perform_experiment(1.0, 2.0, 3.0, 2.5, 10000)

	# Plotting cumulative returns for each experiments in a logarithmic scale
	plt.plot(Experiment1, label = 'Optimistic Initial = 100')
	plt.plot(Experiment2, label = 'Optimistic Initial = 10')
	plt.plot(Experiment3, label = 'Optimistic Initial = 2.5')
	plt.legend()
	plt.xscale('log')
	plt.show()