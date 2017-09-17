import numpy as np 

def array_generator():
	array = np.array([(1, 2, 3, 4, 5), (10, 20, 30, 40, 50)])
	return array

def multiply_by_number(array, number):
	print(array)
	multiplied = array * number
	print(multiplied)
	return multiplied

def divide_by_number(array, number):
	# Either the numer or the elements of the array need to be double
	# to get a double value

	print(array)
	multiplied = array / number
	print(multiplied)
	return multiplied

def addition(array_1, array_2):
	return array_1 + array_2

def elemtwise_mul(array_1, array_2):
	return array_1 * array_2

if __name__ == "__main__":
	# -----------------------------------------------------
	x = array_generator()
	two_x = multiply_by_number(x, 2)
	half_x = divide_by_number(x, 2)
	added = addition(two_x, half_x)
	element_multiplied = elemtwise_mul(x, two_x)

	# -----------------------------------------------------

	print('Y')
	y = np.array([(1, 2 ,3), (4, 5, 6)]) # !
	print(y)	

	print('Z')
	z = np.array([(1, 2), (3, 4), (5, 6)]) # !
	print(z)

	print('D')
	d = np.dot(y, z)
	print(d)