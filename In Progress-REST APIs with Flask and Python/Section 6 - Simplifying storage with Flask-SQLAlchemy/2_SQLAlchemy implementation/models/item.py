from db import db

class ItemModel(db.Model):
	__tablename__ = 'items'
	id = db.Column(db.Integer, primary_key = True)
	name = db.Column(db.String(100))
	price = db.Column(db.Float(precision = 2))

	def __init__(self, name, price):
		self.name = name
		self.price = price

	def json(self):
		return {'name' : self.name, 'price' : self.price}

	@classmethod
	def find_by_name(cls, name):
		# SELECT * FROM items WHERE name = name LIMIT 1;
		return cls.query.filter_by(name = name).first()

	def save_to_db(self):
		# SQL alchemy will both update and insert the item to the db
		db.session.add(self) # self contains of name and price
		db.session.commit()

	def delete_from_db(self):
		db.session.delete(self) # self contains of name and price
		db.session.commit()
	