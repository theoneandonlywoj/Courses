import gym

# Make the environment
env = gym.make('CartPole-v0')

# Reset the environment to default beginning
env.reset()

for _ in range(1000):
    # Render the env
    env.render()

    # Take a random action
    env.step(env.action_space.sample())
