import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def random_integer_array():
	
	x = np.random.rand(4)
	print('Given array: ')
	print(x)

	return x

def access_elements(array):
	indexes = np.array([1, 1, 2, 3])
	elements = array[indexes]
	print('Requested elements:')
	print(elements)
	return elements


if __name__ == "__main__":
	x = random_integer_array()
	access_elements(x)

	



