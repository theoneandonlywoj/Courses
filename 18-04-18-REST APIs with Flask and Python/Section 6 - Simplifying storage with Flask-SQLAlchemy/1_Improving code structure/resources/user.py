import sqlite3 
from flask_restplus import Resource, reqparse
from models.user import UserModel

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
		if UserModel.find_by_username(data['username']) is not None:
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
