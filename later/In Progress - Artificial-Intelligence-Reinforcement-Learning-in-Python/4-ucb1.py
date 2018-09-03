# https://books.google.ca/books?id=_ATpBwAAQBAJ&lpg=PA201&ots=rinZM8jQ6s&dq=hoeffding%20bound%20gives%20probability%20%22greater%20than%201%22&pg=PA201#v=onepage&q&f=false
from __future__ import print_function, division
from builtins import range

from tqdm import tqdm
import numpy as np
import matplotlib.pyplot as plt

# Create a bandit class
class Bandit:
  def __init__(self, m):
    self.m = m
    self.mean = 0
    self.N = 0

  def pull(self):
    return np.random.randn() + self.m

  def update(self, x):
    self.N += 1
    self.mean = (1 - 1.0 / self.N) * self.mean + 1.0 / self.N * x

# Calculate UCB1
class Calculate:
  @staticmethod
  def ucb(mean, n, nj):
    if nj == 0:
      return float('inf')
    return mean + np.sqrt(2*np.log(n) / nj)

class Experiment():
  @staticmethod
  def run_experiment(m1, m2, m3, N):
    bandits = [Bandit(m1), Bandit(m2), Bandit(m3)]

    data = np.empty(N)
    
    for i in tqdm(range(N)):
      j = np.argmax([Calculate.ucb(b.mean, i + 1, b.N) for b in bandits])
      x = bandits[j].pull()
      bandits[j].update(x)

      # for the plot
      data[i] = x
    cumulative_average = np.cumsum(data) / (np.arange(N) + 1)

    # for b in bandits:
    #   print("bandit nj:", b.N)

    # plot moving average ctr
    plt.plot(cumulative_average)
    plt.plot(np.ones(N)*m1)
    plt.plot(np.ones(N)*m2)
    plt.plot(np.ones(N)*m3)
    plt.xscale('log')
    plt.show()

  # for b in bandits:
  #   print(b.mean)

    return cumulative_average

if __name__ == '__main__':
  ucb = Experiment.run_experiment(1.0, 2.0, 3.0, 100000)

  # log scale plot
  
  plt.plot(ucb, label='ucb1')
  plt.legend()
  plt.xscale('log')
  plt.show()


  # linear plot
  plt.plot(ucb, label='ucb1')
  plt.legend()
  plt.show()
