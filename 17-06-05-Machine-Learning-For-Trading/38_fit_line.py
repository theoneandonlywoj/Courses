import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as spo

def error(line, data):
    '''
    Compute error between given model and observed data.
    
    Parameters:
    -----------
    line: tuple, list, array - C0 (slope) and C1 is Y-intercept
    data: 2D array
    '''
    x = data[:, 0]
    y = data[:, 1]
    C0 = line[0]
    C1 = line[1]
    error = np.sum((y - (C0 * x + C1)) ** 2)    
    return error
    
def generate_line(C0, C1, sigma):
    '''
    Generate a line with noise.
    
    Parameters:
    -----------
    C0: slope
    C1: Y_intercept
    sigma: noise
    '''
    print ("Original Line: C0 = {}, C1 = {}".format(C0, C1))
    x_orig = np.linspace(0,10,21)
    y_orig = C0 * x_orig + C1 
    plt.plot(x_orig,y_orig, 'b--', linewidth = 2.0, label = 'Original Line')
    
    ### Generate Noisy Points 
    noise_sigma = sigma
    noise = np.random.normal(0, noise_sigma, y_orig.shape)
    data = np.asarray([x_orig,y_orig + noise]).T
    return data
   
def fit_line(data, error_func):
    ''' Fit a line to given data, using a supplied error function. 
    Parameters
    ----------
    data: 2D array where each row is a point (X0,Y)
    error: function that computes the error between a line and observed data
    
    Returns a line that minimizes the error function 
    '''
    
    # Intializing as a horizontal line with y = y.mean
    l_init = np.float32([0, np.mean(data[:, 1])])
    
    # Plotting the initial guess
    X_min = -1
    X_max = 5
    X_range = np.float32([X_min, X_max ])
    C0_init = l_init[0]
    C1_init = l_init[1]
    Y_init = C0_init * X_range + C1_init
    plt.plot(X_range, Y_init, 'm--', label = 'Initial guess')
    
    # Minimizing error function
    result = spo.minimize(error, l_init, args = (data,), method = 'SLSQP', options = {'disp': True})
    
    return result.x
    
def run():
    data = generate_line(1, 2, 2)
    fited_line = fit_line(data,error)
    C0_fited = fited_line[0]
    C1_fited = fited_line[1]
    X_data = data[:, 0]
    Y_data = data[:, 1]
    plt.plot(X_data, Y_data, 'go', label = 'Data Points')
    plt.plot(X_data,C0_fited * X_data + C1_fited, 'r--', label = 'fitted line')
    plt.legend(loc = 'upper left')
    plt.show()
    print ('Fitted Line: C0 = {}, C1 = {}'.format(C0_fited, C1_fited))
    
if __name__ == "__main__":
    run()

    