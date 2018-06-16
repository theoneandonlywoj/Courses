# Docker Registry
## Docker Hub
### Basic Terminology
- A Registry manages and stores image repositories
- An Image Repository is a collection of tagged images
- A tag is generally a variation or version of a particular docker image
- A namespace is generally the account or team identified that manages that repository
- Syntax:
  - <registry>/<namespace>/<repository-name>:<tag>
  - example:
    - docker.io/library/openjdk:8-jdk

### Commands
#### Login (DockerHub = docker.io):
docker login docker.io -> provide login and password

OR 

docker login --username my_username --password password

#### Re-tag (must contain your username):
docker tag ubuntu-with-wget:latest arcyfelix/ubuntu-with-wget:1.0

#### Push:
docker push arcyfelix/ubuntu-with-wget:1.0

#### Logout:
docker logout docker.io

### Webhooks
A webhook can be used to trigger testing with Jenkins or deployment or other activities.

### Additional commands
#### Save Docker Image to .tar
docker save my-image-name > some-name.tar

#### Load Docker Image from .tar
docker load -- input some-name.tar

## Docker Hub Registry Alternatives
### Self-hosted Alternatives
- Generic Private Docker Registry
- Portus

### Other Hosted Alternatives
- Quay.io
- Azure Container Registry
- Amazon Elastic Container Registry
- Google Container Registry
- Private Docker Registry
- Artifactory