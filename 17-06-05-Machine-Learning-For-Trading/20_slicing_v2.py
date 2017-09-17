import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def random_integer():
	d = np.random.randint(10, 20, size = (6, 6)) # integers in [low, high) as 2D array
	print(d)
	print('*' * 50)
	return d

def all_info(input_array):
	shape = input_array.shape
	print('Shape:', shape)
	print('Number of objects in the array:', input_array.size)
	print('Type:', input_array.dtype)
	print('Dimentionality:', len(shape))
	print('The array has', shape[0] , 'rows.')
	print('The array has', shape[1] , 'columns.')
	print('*' * 50)

def very_specific_slicing(input_array):
	output = array[:, 0:5:2] # Columns from 0 to 3, with step 2.
	print(output)
	print('*' * 50)
	return output

if __name__ == "__main__":
	print("Random integers")
	array = random_integer()
	very_specific_slicing(array)


