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
				item.save_to_db()
			except:
				# Returning 500 - Internal Server Error
				return {'message' : 'An error occurred inserting the item.'}, 500
			
			return item.json(), 201

	def delete(self, name):
		item = ItemModel.find_by_name(name)
		if item is not None:
			item.delete_from_db()
		return {'message' : 'Item deleted.'}

	def put(self, name):
		# Parsing 
		request_data = Item.parser.parse_args()
		item = ItemModel.find_by_name(name)
		
		if item is None:
			item = ItemModel(name, request_data['price'])
		else:
			item.price = request_data['price']
		item.save_to_db()

		return item.json()
			
class ItemList(Resource):
	def get(self):
		return {'items' : [item.json() for item in ItemModel.query.all()]}