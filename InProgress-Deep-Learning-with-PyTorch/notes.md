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
