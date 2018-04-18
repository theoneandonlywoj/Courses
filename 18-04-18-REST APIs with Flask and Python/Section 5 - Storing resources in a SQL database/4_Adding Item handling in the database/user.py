import sqlite3 
from flask_restplus import Resource, reqparse

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

class UserRegister(Resource):
	# Parameter parsing
	parser = reqparse.RequestParser()
	parser.add_argument('username',
						type = str, 
						required = True,
						help = "Username is required!" )
	parser.add_argument('password',
						type = str, 
						required = True,
						help = "Password is required!" )
	def post(self):
		data = UserRegister.parser.parse_args()

		# Preventing user duplication
		if User.find_by_username(data['username']) is not None:
			return {"message" : "User with that username already exists."}, 400
		else:
			# Connection
			connection = sqlite3.connect('data.db')
			cursor = connection.cursor()

			# id is auto-incrementing so it needs to be setup to null
			register_query = "INSERT INTO users VALUES (NULL, ?, ?)"
			cursor.execute(register_query, (data['username'], data['password'],))

			connection.commit()
			connection.close()

			return {"message": "User created successfully!"}, 201
