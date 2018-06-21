# Run Docker Container Interactively
## Running latest version of Ubuntu
docker run -it ubuntu:latest /bin/bash

## Removing container after exit by adding --rm
docker run it --rm ubuntu:latest /bin/bash

## Accessing an already running container
docker exec it id-of-the-container /bin/bash