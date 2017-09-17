import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as spo 

def error_poly(C,data):
    '''
    Compute error between given polynomial and observd data. 
    
    Parameters
    ----------
    C: numpy.poly1d object or equivalent array of polynomial coefficients
    data: 2D array as (x,y) 
    
    Returns error as a single real value. '''
    x = data[:, 0]
    y = data[:, 1]
    
    error = np.sum((y - np.polyval(C, x)) ** 2 )
    return error
    
def fit_poly(data, error_func, degree = 4):
    '''
    Fit a polynomial to given data, using supplied error function. 
    Parameters
    ----------
    data: 2D array where each row is a point (X0,Y)
    error_func: function that computes the error between a poynomial and observed data
    
    Returns polynomial that minimizes the error function '''
    
    C_init = np.poly1d(np.ones(degree + 1, dtype = np.float32))
    
    #plot initial guess (optional) 
    x = np.linspace (-5, 5, 21)
    y_init = np.polyval(C_init, x)
    plt.plot(x, y_init, 'm--', linewidth = 2.0, label = 'Initial Guess')
    # Call Optimizer
    result = spo.minimize(error_func, 
                          C_init, 
                          args = (data,), method = 'SLSQP', options = {'disp':True} )
    return np.poly1d(result.x)
    
def generate_poly(noise_sigma = 5):
    ### Fitting Higher Order Polynomials 
    C_init = np.poly1d(np.array([1, -10, -50, 60, 50], dtype = np.float32))
    x = np.linspace(-5, 5, 21)
    y = np.polyval(C_init,x)
    noise = np.random.normal(0, noise_sigma, y.shape)
    
    data =  np.asarray([x, y+ noise]).T
    return x, y, data
    
def run():
    x, y, data = generate_poly()
    fitted_poly = fit_poly(data,error_poly)
    
    plt.plot(x, y, 'b-', label = 'Original Line')
    plt.plot(data[:,0], data[:,1], 'go', label = 'Data Points')
    plt.plot(data[:,0], np.polyval(fitted_poly,data[:,0]), 'r--', label = 'Fitted Line')
    plt.legend(loc = 'upper right')
    plt.show()
    
if __name__ == '__main__':
    run()