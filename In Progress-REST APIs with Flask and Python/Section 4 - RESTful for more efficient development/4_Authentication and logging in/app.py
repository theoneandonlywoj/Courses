from flask import Flask, request
from flask_restplus import Resource, Api
from flask_jwt import JWT, jwt_required

from security import authenticate, identity

app = Flask(__name__)
app.secret_key = 'Wojciech'
api = Api(app)

# JWT creates a new endpoint /auth
jwt = JWT(app, authenticate, identity)

# List for storing the data.
items = []

class Item(Resource):
	@jwt_required()
	def get(self, name):
		# Code quality improvement
		# 'next' normally throws an error if there is no values in the filter function
		# that can be replaced by a default value, in this case 'None'
		item = next(filter(lambda x: x['name'] == name, items), None)
		# If the item is not found
		return {'item' : item}, 200 if item is not None else 404

	def post(self, name):
		# Ensuring that there is only one item with that name
		# 'next(filter(lambda x: x['name'] == name, items), None)' will be None 
		# only if the new existing item does not exist in the list.

		if next(filter(lambda x: x['name'] == name, items), None) is not None:
			return {'message' : "An item with name '{} already exists!".format(name)}, 400
			
		# 'force = True' forces the app to automatically format json 
		# even if the header does not Content-type
		# 'silent = True' is another option
		# It does not return error
		request_data = request.get_json()
		item = {'name' : request_data['name'],
				'price' : request_data['price']}
		items.append(item)
		return item, 201

class ItemList(Resource):
	def get(self):
		return {'items' : items}


api.add_resource(Item, '/item/<string:name>')
api.add_resource(ItemList, '/items')


app.run(port = 5000, debug = True)