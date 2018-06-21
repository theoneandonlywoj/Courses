# MySQL with PHP Adminer
## MySQL
#### Documentation:
https://hub.docker.com/_/mysql/

#### Initialize MySQL container (version 5.7)
##### -e is used to set an environment variable
docker run --name our-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7

## PHP Adminer
### Documentation:
https://hub.docker.com/_/adminer/

#### Start the container with link to the MySQL database
docker run --link "our-mysql:db" -p 8080:8080 adminer

## Access Adminer:
http://192.168.99.100:8080/

## Login
Username = root

Password = password

Database = mysql