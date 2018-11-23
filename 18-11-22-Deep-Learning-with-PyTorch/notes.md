# Deep-Learning-with-PyTorch
## Introduction to Neural Networks
1) The Student Admission example:
If there are n-features we can use for the prediction and formulate the solution as Wx + b
then the dimension are:
- W (1 by n)
- x (n by 1)
- b (1 by 1)

2) Logical Operators as Perceptron
Logical operators can be represented in a form of perceptrons (example in file 01-AND-operator-as-perceptron).

It is possible to go from an AND operator to an OR operator by:
- increases both weights
- decreasing the magnitude of the bias

XOR operator cannot be created by a single line split.
However, we can compose it using operators NOT and AND and OR.

3) Error function
In order to use an error function with gradient descent, the function needs to be:
- continuous
- differentiable

On top of that, we need to make discrete prediction represented as continuous predictions.

4) One-hot encoding
A very common way to clearly encode multiple classes is to encode each classes
as a set of binary variables.
If the objects can be of a single class, only one binary value can be equal to one.

5) Maximum Likelihood
In order to pick the best model, we choose the model that gives the existing labels the highest probability.

6) Cross-entropy
Cross-entropy ties events and probabilities. If the probability of the events are high, then the cross-entropy is low.

7) Gradient descent algorithm
- Start with random weights (and biases)
- For every point (or a batch) and every feature calculate the partial derivative of the error function with respect to a weight or a bias,
and update the weight with a given learning rate.
- Repeat until error is small

8) Derivative of the sigmoid function
The derivative of the sigmoid function is given as the sigmoid function multiplied by the result of substracting sigmoid function from 1.

9) Types of errors
- Overfitting - fitting to the training data too much, resulting in the model not being able to represent the real relation between the input and the output
- Underfitting - trying to solve the problem with a model that is not good enough and it is not capable of learning the input-output relation.

10) Training Optimization Techiniques
- Early Stopping
- Regularization (L1 and L2)
- Dropout
- Random Restart - starting from few different random places
- Batch training (Stochastic Gradient Descent)
- Decaying learning rate

## Convolutional Neural Networks
1) Model validation
The dataset should be split into train, test and validation dataset.
During the training process, the network uses the training dataset and it is evaluated by calculating accuracy (or error) over the validation dataset.
Using early stopping, we can stop training when the network does not improve to save resources. 