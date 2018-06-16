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