# Installing Docker Compose
## Documentation:
https://docs.docker.com/compose/install/#prerequisites

## Checking Docker Compose Version
docker-compose -v

## Running the composition (as a deamon)
docker-compose -f my_first_docker_compose_template.yml up -d

## Accessing command line of one of the containers
docker exec -it my_container_name /bin/bash

## Ping Tomcat1 from Tomcat2
docker exec -it 01dockercomposeinstallation_tomcat2_1 /bin/bash

ping tomcat1

## Test the environment variable
docker exec -it 01dockercomposeinstallation_tomcat2_1 /bin/bash

echo $TEST_ENV_VARIABLE