# Notes
## Building Dockerfile for docker-compose on the fly
- If you run docker-compose with no changes to the file changes to an already build docker image will not apply.
- In order to force docker-compose to re-built if, run:

docker-compose -f building_docker_container_on_the_fly_for_docker_compose.yml up -d  --build

## Pulling images for docker-compose
- The file must be named docker-compose.yml
docker-compose pull

## Docker Events
docker events

## Restart docker-compose service without deleting the network (the file must be named docker-compose.yml)
docker-compose stop