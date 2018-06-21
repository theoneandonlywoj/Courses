# Docker CLI and Useful Commands
## Print IDs of all running containers
docker ps -q

## Stop a container
docker stop my_container_id

OR 

docker stop my_container_name

## Remove a container
docker rm my_container_id

OR 

docker rm my_container_name

## Stop all containers
docker stop $(docker ps -q)

## Remove all containers (even those not running)
docker stop $(docker ps -a -q)

## Filtering docker ps
### Filter on a status
docker ps --filter status=created

docker ps --filter status=runnning

docker ps --filter status=restarting

docker ps --filter status=exited

docker ps --filter status=removing

docker ps --filter status=paused

docker ps --filter status=dead

### Filter on a name
docker ps -- filter name=my_container

### Filter on a label
docker ps --filter "label=color"

docker ps --filter "label=color=blue"

### Filter on a health
docker ps --filter health=starting

docker ps --filter health=healthy

docker ps --filter health=unhealthy

docker ps --filter health=none
## Docker inspect
docker inspect my_container_id 

OR 

docker inspect my_container_name
