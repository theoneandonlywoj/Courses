from flask import Flask
from flask_restplus import Api
from flask_jwt import JWT

from security import authenticate, identity

from resources.user import UserRegister
from resources.item import Item, ItemList


app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///data.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.secret_key = 'Wojciech'
api = Api(app)

# JWT creates a new endpoint /auth
jwt = JWT(app, authenticate, identity)

api.add_resource(Item, '/item/<string:name>')
api.add_resource(ItemList, '/items')
api.add_resource(UserRegister, '/register')

if __name__ == '__main__':
	from db import db
	db.init_app(app)
	app.run(port = 5000, debug = True)