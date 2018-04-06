from flask import Flask, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)


# List for storing the data.
items = []

class Item(Resource):
	def get(self, name):
		for item in items:
			if item['name'] == name:
				return item
		# If the item is not found
		return {'item' : None}, 404

	def post(self, name):
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