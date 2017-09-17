import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as spo

def f(X):
    '''
    Given a scalar X, he function returns a value according to the function.
    f(X) = (X - 1.5) ** 2 + 0.5
    '''
    Y = (X - 1.5) ** 2 + 0.5
    print("Values for the function:")
    print("X = {}, Y = {}".format(X, Y))
    return Y
    
def minimizer():
    X_start = 5.0
    result = spo.minimize(f, 
                          X_start, 
                          method = 'SLSQP', 
                          options = {'disp': True})
    print('Minima found at:')
    print("X = {}, Y = {}".format(result.x, result.fun))

    # Plotting the values and marking minima
    X_plot = np.linspace(0.5, 2.5, 21)
    Y_plot = f(X_plot)
    plt.plot(X_plot, Y_plot)
    plt.plot(result.x, result.fun, 'ro') # ro == red dot
    plt.title("Minima of the function")
    plt.show()
     
if __name__ == "__main__":
    minimizer()