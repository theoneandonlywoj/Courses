# Run Docker container in the background
## -d = running in the background
## -p host_post:container_port
docker run -d -p 84:80 webapp-wojciech:latest

## Killing all running containers
docker kill $(docker ps -q)

## Access the website at IP provided when starting Docker
f.e.:
http://192.168.99.100:84/index.html