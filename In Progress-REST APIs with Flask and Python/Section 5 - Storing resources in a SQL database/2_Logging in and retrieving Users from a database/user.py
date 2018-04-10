import sqlite3 

class User:
	def __init__(self, _id, username, password):
		self.id = _id
		self.username = username
		self.password = password

	@classmethod
	def find_by_username(cls, username):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "SELECT * FROM users WHERE username = ?"
		# Parameters MUST ALWAYS be in form of a TUPLE!
		result = cursor.execute(query, (username, ))
		# If the result set does not contain any values row = None
		row = result.fetchone()

		if row is not None:
			# *row is like *args, cls in this example is class User
			user = cls(*row)
		else:
			user = None

		connection.close()

		return user

	@classmethod
	def find_by_id(cls, id):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "SELECT * FROM users WHERE id = ?"
		# Parameters MUST ALWAYS be in form of a TUPLE!
		result = cursor.execute(query, (id, ))
		# If the result set does not contain any values row = None
		row = result.fetchone()

		if row is not None:
			# *row is like *args, cls in this example is class User
			user = cls(*row)
		else:
			user = None

		connection.close()

		return user