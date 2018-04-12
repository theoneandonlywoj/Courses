import sqlite3

# Initializing a connection
connection = sqlite3.connect('data.db')
# Initializing a cursor
cursor = connection.cursor()

# Creating USERS table
# id column being automatically incremented
create_table_query = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username text, password text)"
cursor.execute(create_table_query)

# Commiting the insert(s)
connection.commit()
# Closing the connection
connection.close()

