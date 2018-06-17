# Docker Swarm
## Getting Started with Docker Swarm
- Docker Swarm is a cluster od docker hosts that work together to deploy containers
- The coordination between the hosts if often referred to as orchestration.

### Cluster
A cluster is made of 2 different types of docker hosts (nodes):
- Manager
- Worker

#### Manager (Master)
A Manager deals with issuing out commands to the other nodes.
Another role of a manager is making sure the cluster is staying connected, nodes are up etc.
In case if there are multiple managers (which is recommended), one manager is considered a leader.
The leader is the primarily node that issues out the commands etc.
Other managers stay updated with the status of the leader so they can replace it in case something goes
wrong with the leader.

#### Worker (Slave)
A Worker simply receives and carriers out commands passed by managers.

### Benefits
- Load Balancing
- Highly Available
- Scaling capabilities
- Rolling updates
- Security
- Self-Healing

## Creating a Swarm 
### Basic Information
Command "docker swarm init" run from inside the docker machine will not work due to IP conflicts (the machine has two IP addresses).
It can be overcome by finding IP of the local machine (run outside of the default machine):
docker-machine ip default 

### SSH to the default machine:
docker-machine ssh default

### Initialize a Swarm as below:
docker swarm init --advertise-addr 192.168.99.100

It returns a message:
"Swarm initialized: current node (z1ou1rbxqpvzjwarmwmzof6mw) is now a manager. 
### Notes on adding a Worker
To add a worker to this swarm, run the following command:

docker swarm join --token SWMTKN-1-62xsy5jjbqh7f85ofmvfb99xexmhp55nwtdkafkfxeo6so1qbh-73dt1w52t667t8cupt529kruv 192.168.99.100:2377                                   

#### Notes on adding a Manager
To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions."

### Create additional docker machine (Swarm node)
docker-machine create my-new-machine

### SSH to the new docker machine (Swarm node)
docker-machine ssh my-new-machine

### Add the node to the Swarm as a Worker (from inside the node)
docker swarm join --token SWMTKN-1-62xsy5jjbqh7f85ofmvfb99xexmhp55nwtdkafkfxeo6so1qbh-73dt1w52t667t8cupt529kruv 192.168.99.100:2377

### Check information on the node to ensure the success:
docker info

### It will return a lot of information, the most important for now is the Swarm section:
- Swarm: active 
- NodeID: mcmu9h2scqb3n2uu8sw0bkun0  
- Is Manager: false          
- Node Address: 192.168.99.101   
- Manager Addresses: 192.168.99.100:2377  

### Ensure the Leader has access to the Worker node (from inside of the Leader node):
docker node ls

### Remove a node from the Swarm (from inside of the node):
docker swarm leave

### Leave as a Manager / Leader:
Leaving as a Manager or Leader needs to be forced as below:

docker swarm leave --force

### Promote Worker to Manager (from inside of the Leader)
#### Its new manager status will be "Reachable"
docker node promote my-new-

### Demote Manager to Worker (from inside of the Leader)
#### Its new manager status will be " "
docker node demote my-new-machine

## Docker Services
### Create a Service (a Nginx example, replicated mode by default)
docker service create --replicas 5 nginx

### Create a Service (global mode)
docker service create --mode global nginx

### List all Docker Services
docker service ls

### Get information about the Service
docker service ps my_service_id

### Scale up the Service
docker service update --replicas 6 my_service_id

### Scale up after adding new nodes (global mode)
docker service update --replicas 6 --mode global my_service_id

### Changing ports (rolling update)
docker service update --publish-add 80:80 my_service_id

### Run all containers on a specific machine
docker service update --constraint-add "node.hostname==my-new-machine" my_service_id

### Rollback
docker service rollback my_service_id