import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def generate_random_uniform():
	'''Generate an uniformly sampled values from 0 (inclusive) to 1 (exclusive)'''
	x = np.random.random((5, 4)) # array shape as tuple
	print(x)
	print('*' * 50)
	return x
def generate_random_normal(mean = 0, std = 1):
	'''Generate a zero mean and unit standard deviation data'''
	x = np.random.normal(mean, std, size = (2, 4, 2)) # array shape as tuple
	print(x)
	print('*' * 50)
	return x 

def random_integer():
	x = np.random.randint(1) # A single integer in [0, 10)
	y = np.random.randint(10, 20) # A single integer in [low, high)
	z = np.random.randint(10, 20, size = 5) # 5 integers in [low, high) as 1D array
	d = np.random.randint(10, 20, size = (2, 3)) # integers in [low, high) as 2D array

	print(x)
	print('*' * 50)
	print(y)
	print('*' * 50)
	print(z)
	print('*' * 50)
	print(d)
	print('*' * 50)

def all_info(input_array):
	shape = input_array.shape
	print('Shape:', shape)
	print('Number of objects in the array:', input_array.size)
	print('Type:', input_array.dtype)
	print('Dimentionality:', len(shape))
	print('The array has', shape[0] , 'rows.')
	print('The array has', shape[1] , 'columns.')
	print('*' * 50)

if __name__ == "__main__":
	print('Uniformal')
	x = generate_random_uniform()
	all_info(x)

	print('Normal distribution')
	y = generate_random_normal(mean = 50, std = 10)
	all_info(y)
	print("Random integers")
	random_integer()



