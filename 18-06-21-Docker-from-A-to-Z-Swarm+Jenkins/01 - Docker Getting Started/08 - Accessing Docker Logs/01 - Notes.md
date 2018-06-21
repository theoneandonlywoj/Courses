# Accesing Docker Logs
## Everything run with CMD will be prompted in logs
## They can be accessed with command below:
docker logs my_container_name
OR 
docker logs my_container_id

## You can grab logs interactively:
docker logs -f my_container_name
OR 
docker logs -f my_container_id

## Or limit number of lines:
docker logs --tail 10 my_container_name
OR 
docker logs --tail 10 my_container_id
