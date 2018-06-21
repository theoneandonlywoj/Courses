# Docker Stack Deploy
## Deploying a stack
docker stack deploy --compose-file stack-to-be-deployed.yml stackdemo

## Check the services
docker service ls

## Check the tasks (images)
docker service ps stackdemo

## Remove the stack
docker stack rm stackdemo