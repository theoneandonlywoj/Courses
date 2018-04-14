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
		item = Item.find_by_name(name)
		if item is not None:
			return item
		else:
			return {'message' : 'Item not found.'}, 404

	@classmethod
	def find_by_name(cls, name):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "SELECT * FROM items WHERE name = ?"
		result_set = cursor.execute(query, (name,))
		# Name assumed to be unique
		row = result_set.fetchone()

		connection.close()

		if row is not None:
			return {'item': {'name' : row[0], 
							 'price' : row[1]}}

	@classmethod
	def insert(cls, item):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "INSERT INTO items VALUES(?, ?)"
		cursor.execute(query, (item['name'], item['price']))

		connection.commit()
		connection.close()


	def post(self, name):
		if Item.find_by_name(name) is not None:
			return {'message' : 'Item already exists.'}, 400
		else:
			# Parsing 
			request_data = Item.parser.parse_args()
			item = {'name' : name, 
					'price' : request_data['price']}

			# Dealing with possible insertion error
			try:
				Item.insert(item)
			except:
				# Returning 500 - Internal Server Error
				return {'message' : 'An error occurred inserting the item.'}, 500
			
			return item, 201

	def delete(self, name):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "DELETE FROM items WHERE name = ?"
		cursor.execute(query, (name,))
		
		connection.commit()
		connection.close()
		return {'message' : 'Item deleted.'}

	@classmethod
	def update(cls, item):
		connection = sqlite3.connect('data.db')
		cursor = connection.cursor()

		query = "UPDATE items SET price = ? WHERE name = ? "
		cursor.execute(query, (item['price'], item['name']))

		connection.commit()
		connection.close()


	def put(self, name):
		# Parsing 
		request_data = Item.parser.parse_args()
		item = Item.find_by_name(name)
		updated_item = {'name' : name, 
						'price' : request_data['price']}
		if item is None:
			try:
				Item.insert(updated_item)
			except: 
				return {'message' : 'An error occurred inserting the item.'}, 500
			return {'message' : 'Item created.'}
		else:
			try:
				Item.update(updated_item)
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