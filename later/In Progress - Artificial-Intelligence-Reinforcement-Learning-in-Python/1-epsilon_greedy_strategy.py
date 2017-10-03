import numpy as np

# Epsilon - probability of exploration
eps = 0.05

#Making a defined number of actions
for action in range(20):
    # Generating a random number
    p = np.random.random()
    
    # Exploring
    if p < eps:
        print('Random action!')
    # Or choosing the best set of actions   
    else:
        print('Current best action.')