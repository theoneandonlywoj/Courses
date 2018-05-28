import tensorflow as tf
import gym
import numpy as np

###############################################
######## PART ONE: NETWORK VARIABLES #########
#############################################

# Observation Space has 4 inputs
num_inputs = 4

num_hidden = 4

# Outputs the probability it should go left
num_outputs = 1

initializer = tf.contrib.layers.variance_scaling_initializer()

###############################################
######## PART TWO: NETWORK LAYERS #########
#############################################

# Input placeholder
X = tf.placeholder(tf.float32, 
    shape = [None, num_inputs])
# First hidden layer
hidden_layer_one = tf.layers.dense(X,
                                num_hidden, 
                                activation = tf.nn.relu,
                                kernel_initializer = initializer)
hidden_layer_two = tf.layers.dense(hidden_layer_one,
                                num_hidden,
                                activation = tf.nn.relu,
                                kernel_initializer = initializer)

# Probability to go left
output_layer = tf.layers.dense(hidden_layer_one,
                            num_outputs,
                            activation = tf.nn.sigmoid,
                            kernel_initializer = initializer)

# [ Prob to go left , Prob to go right]
probabilties = tf.concat(axis = 1, 
                        values = [output_layer, 1 - output_layer])

# Sample 1 randomly based on probabilities
action = tf.multinomial(probabilties, num_samples = 1)

# All variables initializer
init = tf.global_variables_initializer()

###############################################
######## PART THREE: SESSION #########
#############################################

saver = tf.train.Saver()

# Run for 50 episodes
episodes = 50
# Number of steps per episode
step_limit = 500
# Initialize empty list for future calculations 
avg_steps = []

# Initialize the environment
env = gym.make("CartPole-v1")

with tf.Session() as sess:
    # Initialize all Tensorflow variables
    init.run()
    for i_episode in range(episodes):
        # Reset the enviroment for a fresh start for each episode
        obs = env.reset()

        for step in range(step_limit):
            env.render()
            # Reshaping observation to ensure proper input structure
            action_val = action.eval(feed_dict = {X: obs.reshape(1, num_inputs)})
            # action_val = [[0]] or [[1]]
            # 0 or 1 can be accessed as action_val[0][0]
            obs, reward, done, info = env.step(action_val[0][0])
            
            # If done = True, break and start a new episode
            if done:
                avg_steps.append(step)
                print('Done after {} steps'.format(step))
                break
print("After {} episodes the average cart steps before done was {}".format(episodes,np.mean(avg_steps)))

# Close the environment
env.close()
