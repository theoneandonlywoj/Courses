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
	
	print(oneD_empty)
	print(twoD_empty)
	print(threeD_empty)

if __name__ == "__main__":
	create_empty_arrays()
	





