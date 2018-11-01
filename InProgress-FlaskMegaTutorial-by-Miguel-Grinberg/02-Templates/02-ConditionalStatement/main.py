# -*- coding: utf-8 -*-
from flask import Flask

# Starting a Flask app
app = Flask(__name__)
# Importing routes
from app import routes