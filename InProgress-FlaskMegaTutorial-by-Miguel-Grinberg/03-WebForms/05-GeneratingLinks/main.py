# -*- coding: utf-8 -*-
from flask import Flask
from config import Config

# Starting a Flask app
app = Flask(__name__)
# Setting config from an object
app.config.from_object(Config)
# Importing routes
from app import routes