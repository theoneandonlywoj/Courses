import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def random_integer_array():
	
	x = np.random.normal(0, 2, size = (2, 3)) # integers in [low, high) as 2D array
	print('Original array: ')
	print(x)

	return x

def replace_one_element(array, row, column, number):
	array[row, column] = number
	print('Changed array: ')
	print(array)
	return array


if __name__ == "__main__":
	x = random_integer_array()
	changed_array = replace_one_element(x, 0, 0, 7)



