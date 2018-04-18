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
		request_data = UserRegister.parser.parse_args()

		# Preventing user duplication
		if UserModel.find_by_username(request_data['username']) is not None:
			return {"message" : "User with that username already exists."}, 400
		else:
			user = UserModel(request_data['username'], request_data['password'])
			user.save_to_db()

			return {"message": "User created successfully!"}, 201
