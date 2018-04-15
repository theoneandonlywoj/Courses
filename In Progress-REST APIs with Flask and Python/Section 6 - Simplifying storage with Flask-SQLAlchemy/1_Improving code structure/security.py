# Flask's secure string comparison
from werkzeug.security import safe_str_cmp

# Importing User class that organizes the user list etc.
from models.user import UserModel

def authenticate(username, password):
	# None is the default value, in case the user is not found
    user = UserModel.find_by_username(username)
    if user is not None and safe_str_cmp(user.password, password):
        return user

# Payload is the content of the JWT token
def identity(payload):
    user_id = payload['identity']
    return UserModel.find_by_id(user_id)