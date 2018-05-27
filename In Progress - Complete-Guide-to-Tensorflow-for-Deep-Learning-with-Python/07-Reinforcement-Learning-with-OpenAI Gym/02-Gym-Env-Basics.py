import gym

# Make the environment
env = gym.make('CartPole-v0')

# Reset the environment to default beginning
# Default observation variable
print('-' * 50)
print("Initial Observation")
observation = env.reset()
print(observation)
print('-' * 50)

for _ in range(20):
    # Random Action
    action = env.action_space.sample()

    # Get 4 observation values
    observation, reward, done, info = env.step(action)

    print('Observation: {}, Reward: {}, Done: {}, Info: {}'.format(observation, reward, done, info))