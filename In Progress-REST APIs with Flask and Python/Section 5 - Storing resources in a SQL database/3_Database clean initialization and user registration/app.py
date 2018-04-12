from flask import Flask, request
from flask_restplus import Resource, Api, reqparse
from flask_jwt import JWT, jwt_required

from security import authenticate, identity

from user import UserRegister

app = Flask(__name__)
app.secret_key = 'Wojciech'
api = Api(app)

# JWT creates a new endpoint /auth
jwt = JWT(app, authenticate, identity)

# List for storing the data.
items = []

class Item(Resource):
	# Adding parser as part of the class
	parser = reqparse.RequestParser()
	parser.add_argument('price',
						type = float, 
						required = True,
						help = "Price is required!" )


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
		
		request_data = Item.parser.parse_args()

		item = {'name' : name,
				'price' : request_data['price']}
		items.append(item)
		return item, 201

	def delete(self, name):
		# Deleting by filtering out the list of items and overwriting it
		# We need to use global items variable
		# Otherwise Python will think that we want to use a variable before assigning it
		global items
		items = list(filter(lambda x: x['name'] != name, items))
		return {'message' : 'Item deleted.'}

	def put(self, name):
		request_data = Item.parser.parse_args()
		# Check if the item exists
		item = next(filter(lambda x: x['name'] == name, items), None)
		# If the item does not exist, create it.
		if item is None:
			item = {'name' : name,
				'price' : request_data['price']}
			items.append(item)
		else:
			item.update(request_data)
		return item


class ItemList(Resource):
	def get(self):
		return {'items' : items}


api.add_resource(Item, '/item/<string:name>')
api.add_resource(ItemList, '/items')
api.add_resource(UserRegister, '/register')

app.run(port = 5000, debug = True)