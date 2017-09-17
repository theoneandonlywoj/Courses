import numpy as np 
''' Look up: https://docs.scipy.org/doc/numpy/reference/routines.random.html '''

def generate_array():
	#x = np.random.rand(4, 5)

	x = np.array([(20, 25, 10, 23, 26, 32, 10, 5, 0),
		(0, 2, 50, 20, 0, 1, 28, 5, 0)])
	print('Given array: ')
	print(x)

	return x

def access_elements(array):
	indexes = np.array([1, 1, 2, 3])
	elements = array[indexes]
	print('Requested elements:')
	print(elements)
	return elements

def masking(array, mask_value):
	masked = array[array < mask_value]
	print('Masked')
	print(masked)

def replacing(array, mask_value, new_value):
	array[array < mask_value] = new_value
	print('Replaced:')
	print(array)


if __name__ == "__main__":
	x = generate_array()
	mean_value = x.mean()
	print("Mean:")
	print(mean_value)

	masking(x, mean_value)

	replacing(x, mean_value, 0)
	

	



