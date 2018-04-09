import sqlite3

# Initializing a connection
connection = sqlite3.connect('data.db')
# Initializing a cursor
cursor = connection.cursor()

# Creating USERS table
create_table_query = "CREATE TABLE users (id int, username text, password text)"
cursor.execute(create_table_query)

# Inserting the first user
user = (1, 'Woj', 'Woj')
insert_query = 'INSERT INTO users VALUES(?, ?, ?)'
cursor.execute(insert_query, user)


# Inserting multiple users
users = [
			(2, 'John', 'Woj'),
			(3, 'Anna', 'Woj')
		]

insert_query = 'INSERT INTO users VALUES(?, ?, ?)'
cursor.executemany(insert_query, users)

# Retrieving the data
select_query = "SELECT * FROM users"
for row in cursor.execute(select_query):
	print(row)

# Commiting the insert(s)
connection.commit()

# Closing the connection
connection.close()

