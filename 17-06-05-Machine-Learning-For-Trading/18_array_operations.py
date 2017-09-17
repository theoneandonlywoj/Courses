import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def random_integer():
	np.random.seed(1337)
	
	d = np.random.randint(10, 20, size = (2, 3)) # integers in [low, high) as 2D array

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

def sum_all_elements(array):
	print('Sum of all elements: ', array.sum())
	return array.sum()

def sum_specific_dir(d, dir):
	if dir == 0:
		sum_dir = d.sum(axis = dir)
		print('Iterated over rows')
		print('Sum of each column: ', sum_dir)
	if dir == 1:
		sum_dir = d.sum(axis = dir)
		print('Iterated over columns')
		print('Sum of each row: ', sum_dir)

	return sum_dir

def find_min(array):
	minimum = array.min()
	return minimum

def find_max(array):
	maximum = array.min()
	return maximum

def find_mean(array):
	mean_value = array.mean()
	return mean_value



if __name__ == "__main__":

	print("Random integers")
	d = random_integer()
	all_info(d)
	sum_of_all = sum_all_elements(d)

	sum_column = sum_specific_dir(d, dir = 0) # Iteration over rows gives sum of for each column
	min_sum_columns = find_min(sum_column)
	print('Minimum value of the sums for columns:', min_sum_columns)

	sum_rows = sum_specific_dir(d, dir = 1) # Iteration over columns gives sum of for each rows
	max_sum_rows = find_max(sum_rows)
	print('Max value of the sums for rows:',max_sum_rows)

	print('Mean value of all values in the array:', find_mean(d))


