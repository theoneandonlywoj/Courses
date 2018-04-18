from flask import Flask, jsonify, request

app = Flask(__name__)

# Example : a store
stores = [
	{
	'name': 'My Store',
	'items':
		[
			{
			'name': 'My item',
			'price': '99999'
			}
		]
	}
]
# Create a store:
#  * POST /store data:{name:} 
@app.route('/store', methods = ['POST'])
def create_store():
	request_data = request.get_json()
	new_store_dict = {
			'name': request_data['name'],
			'items': []
	}
	stores.append(new_store_dict)
	return jsonify(new_store_dict)

# Retrieve information for given store:
#  * GET /store/<string:name> 
@app.route('/store/<string:name>', methods = ['GET'])
def get_store_information(name):
	# Iterate over stores
	for store in stores:
		if store['name'] == name:
			return jsonify(store)
	# Returning message informing the user that no store message matching the name was found.
	return jsonify({'message' : 'store not found'})		
# Retrieve information for all stores:
#  * GET /store
@app.route('/store', methods = ['GET'])
def get_all_stores_information():
	return jsonify({'stores': stores})

# Create an item for given store 
#  * POST /store/<string:name>/item {name:, price:}
@app.route('/store/<string:name>/item', methods = ['POST'])
def create_item_in_store(name):
	request_data = request.get_json()
	for store in stores:
		if store['name'] == name:
			new_item_dict = {
				'name' : request_data['name'],
				'price' : request_data['price']
			}
			store['items'].append(new_item_dict)
			return jsonify(new_item_dict)
	# Returning message informing the user that no store message matching the name was found.
	# Thus the item cannot be added.
	return jsonify({'message' : 'store not found'})	

# Retrieve information about the item
#  * GET /store/<string:name>/item
@app.route('/store/<string:name>/item', methods = ['GET'])
def get_item_in_store(name):
	for store in stores:
		if store['name'] == name:
			return jsonify(store['items'])
	# Returning message informing the user that no item message matching the name was found.
	return jsonify({'message' : 'item not found'})	


# Run the application
app.run(port = 5000)

# Endpoints tested
# Don't forget to set Content-Type to application/json in the hearder while using POST.