import sqlite3
from flask_restplus import Resource, reqparse
from flask_jwt import jwt_required

class Item(Resource):
	# Adding parser as part of the class
	parser = reqparse.RequestParser()
	parser.add_argument('price',
						type = float, 
						required = True,
						help = "Price is required!" )


	@jwt_required()
	def get(self, name):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "SELECT * FROM items WHERE name = ?"
		result_set = cursor.execute(query, (name,))
		# Name assumed to be unique
		row = result_set.fetchone()

		connection.close()

		if row is not None:
			return {'item': {'name' : row[0], 'price' : row[1]}}
		else:
			return {'message' : 'Item not found.'}, 404

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