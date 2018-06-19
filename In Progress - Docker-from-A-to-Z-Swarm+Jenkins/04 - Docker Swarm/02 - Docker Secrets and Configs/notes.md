# Docker Secrets and Configs
Both secrets and configs are mounted files inside a container of a swarm service at runtime
Main differences are:
- Location:
	- Secrets can only be mounted to /run/secrets/my_secret_name
	- Configs default to /my_config_name, can be mounted anywhere 
- Encryption:
	- Secrets are encrypted and then decrypted within the container

## Get Docker Swarm Certificate
docker swarm ca

## Export the certificate as a secret / config
docker swarm ca | docker secret create my_swarm_ca -

docker swarm ca | docker config create my_swarm_ca -

## Export the certificate to a file
docker swarm ca > my_swarm_ca.crt

## Create a secre / config from a file
docker secret create my_swarm_ca_2 ./my_swarm_ca.crt

docker config create my_swarm_ca_2 ./my_swarm_ca.crt

## Inspect a secret / config
docker secret inspect my_swarm_ca_2

docker config inspect my_swarm_ca_2