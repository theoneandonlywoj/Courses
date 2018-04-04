from flask import Flask 

app = Flask(__name__)

# Endpoint to the home page
# f.e. http://www.something.com/
@app.route('/') 
# Name of the method does not matter
def home():
	return "Hello world!"


app.run(port = 5000)