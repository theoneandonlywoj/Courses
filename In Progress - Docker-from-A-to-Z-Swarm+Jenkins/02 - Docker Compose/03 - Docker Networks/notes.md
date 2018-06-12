# Docker Network
## Basic Information
Docker containers created with docker-compose are abled to communicated with each other because the create a simple, isolated network on the docker host.

Networking in that nature is very beneficial, because it segments the containers, allowing only a specific communication within the network. 

## Listing all currently running Docker Networks
docker network ls

## Help info fo network creation
docker network create --help

## Create a network 
docker network create network_name

## Remove all unused
### -f = force (do not ask for confirmation)
docker network prune -f

## Example:
### Run the first container
docker run -d nginx -t nginx_test

### Run Docker composition
docker-compose -f using_two_different\ networks.yml up -d

### Connect nginx container to the "frontend" network create by the Docker composition
docker network connect 03dockernetworks_backend 29a15b2a7761

### Inspect the network
docker inspect 03dockernetworks_backend

### Disconnect nginx container from the "frontend" network create by the Docker composition
docker network disconnect 03dockernetworks_backend 29a15b2a7761