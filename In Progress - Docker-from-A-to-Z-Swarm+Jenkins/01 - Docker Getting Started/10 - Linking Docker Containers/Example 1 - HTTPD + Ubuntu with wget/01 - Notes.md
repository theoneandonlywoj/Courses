# Linking Docker Containers
## Example
### Run first container in background
docker run -d --name reuse_httpd httpd:latest

### Run second container with link to the first container
#### The can be done using --link "container_name:new_alias"
docker run -it --link "reuse_httpd:web" ubuntu-with-wget /bin/bash

### Test the connection
wget "http://web:80"
cat index.html