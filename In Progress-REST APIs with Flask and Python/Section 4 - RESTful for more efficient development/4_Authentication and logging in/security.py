# Flask's secure string comparison
from werkzeug.security import safe_str_cmp

# Importing User class that organizes the user list etc.
from user import User

users = [
	User(1, 'Woj', 'Woj')
]

username_mapping = {u.username : u for u in users}

userid_mapping = {u.id : u for u in users}

def authenticate(username, password):
	# None is the default value, in case the user is not found
    user = username_mapping.get(username, None)
    if user is not None and safe_str_cmp(user.password, password):
        return user

# Payload is the content of the JWT token
def identity(payload):
    user_id = payload['identity']
    return userid_mapping.get(user_id, None)