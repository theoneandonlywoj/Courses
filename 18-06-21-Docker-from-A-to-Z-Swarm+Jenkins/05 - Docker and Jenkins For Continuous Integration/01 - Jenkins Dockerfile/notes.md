# Initial Jenkins setup
## Build Jenkins from the Dockerfile
docker build . -t my_jenkins:latest  

## Start the service from the Docker Compose file
docker stack deploy -c docker-compose.yml jenkins_service

## Copy admin password from the logs
docker service logs -f jenkins_service_jenkins_service  

## Go to web browser and access provided address:
http://192.168.99.100:8081/

## Unlock Jenkins with the password

## Install suggested plugins
Choose "Install suggested plugins"

## Create First Admin User
Provide:
- Username
- Password
- Confirm Password
- Full name
- E-mail address

## Instance Configuration
Accept or change Jenkins URL

## Jenkins is ready!
Click on "Start using Jenkins"

## Recommended additional plugins
- Go to "Manage Jenkins" -> "Manage Plugins" 
- Pick "Docker", "Docker Slaves" and "Blue Ocean"