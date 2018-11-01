from flask import render_template
from main import app

@app.route('/')
@app.route('/index')
def index():
    user = {'username': 'Wojciech'}
    return render_template('index.html', title='Home', user=user)

@app.route('/no_title/')
@app.route('/index/no_title/')
def index_no_title():
    user = {'username': 'Wojciech'}
    return render_template('index.html', user=user)