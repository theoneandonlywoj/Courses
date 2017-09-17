import numpy as np 

def create_1D_array():
	array = np.array([2, 3, 4])
	print(array)

def create_2D_array():
	array = np.array([(2, 3, 4),(5, 6, 7)])
	print(array)

def create_empty_arrays():
	oneD_empty = np.empty(5)
	twoD_empty = np.empty((5,4))
	threeD_empty = np.empty((5,4,3))

	print('1D array')
	print(oneD_empty)

	print('2D array')
	print(twoD_empty)

	print('3D array')
	print(threeD_empty)

def create_ones_arrays():
	oneD_empty = np.ones(3)
	twoD_empty = np.ones((2,2))
	threeD_empty = np.ones((3,5,2))

	print('1D array')
	print(oneD_empty)

	print('2D array')
	print(twoD_empty)

	print('3D array')
	print('First layer:')
	print(threeD_empty[:, :, 0])
	print('Second layer:')
	print(threeD_empty[:, :, 1])

if __name__ == "__main__":
	create_ones_arrays()
	





