import sqlite3

# Initializing a connection
connection = sqlite3.connect('data.db')
# Initializing a cursor
cursor = connection.cursor()

# Creating USERS table
# id column being automatically incremented
create_table_query = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username text, password text)"
cursor.execute(create_table_query)

create_table_query = "CREATE TABLE IF NOT EXISTS items ( name text, price real)"
cursor.execute(create_table_query)

# Inserting an example item
create_example_item_query = "INSERT INTO items VALUES('test item', 10.99)"
cursor.execute(create_example_item_query)

# Commiting the insert(s)
connection.commit()
# Closing the connection
connection.close()

