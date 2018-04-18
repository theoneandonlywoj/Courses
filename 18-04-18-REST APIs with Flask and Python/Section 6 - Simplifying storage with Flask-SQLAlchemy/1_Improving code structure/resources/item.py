import sqlite3
from flask_restplus import Resource, reqparse
from flask_jwt import jwt_required
from models.item import ItemModel

class Item(Resource):
	# Adding parser as part of the class
	parser = reqparse.RequestParser()
	parser.add_argument('price',
						type = float, 
						required = True,
						help = "Price is required!" )


	@jwt_required()
	def get(self, name):
		item = ItemModel.find_by_name(name)
		if item is not None:
			return item.json()
		else:
			return {'message' : 'Item not found.'}, 404

	def post(self, name):
		if ItemModel.find_by_name(name) is not None:
			return {'message' : 'Item already exists.'}, 400
		else:
			# Parsing 
			request_data = Item.parser.parse_args()
			item = ItemModel(name, request_data['price'])

			# Dealing with possible insertion error
			try:
				item.insert()
			except:
				# Returning 500 - Internal Server Error
				return {'message' : 'An error occurred inserting the item.'}, 500
			
			return item.json(), 201

	def delete(self, name):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "DELETE FROM items WHERE name = ?"
		cursor.execute(query, (name,))
		
		connection.commit()
		connection.close()
		return {'message' : 'Item deleted.'}

	def put(self, name):
		# Parsing 
		request_data = Item.parser.parse_args()
		item = ItemModel.find_by_name(name)
		updated_item = ItemModel(name, request_data['price'])
		if item is None:
			try:
				updated_item.insert()
			except: 
				return {'message' : 'An error occurred inserting the item.'}, 500
			return {'message' : 'Item created.'}
		else:
			try:
				updated_item.update()
			except:
				return {'message' : 'An error occurred updating the item.'}, 500
			return {'message' : 'Item already exists. Updated.'}
			
class ItemList(Resource):
	def get(self):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "SELECT * FROM items"
		result = cursor.execute(query)
		items = []
		for row in result:
			items.append({'name' : row[0], 
						  'price' : row[1]})


		connection.commit()
		connection.close()

		return {'items' : items}