# Running a PHP Docker Container
docker run -t --rm php:latest /bin/bash

## Mounting a volume
## To mount a volume add -v
### Add $pwd to get the full path
### Specify new location (it will be created if needed) inside the container
docker run -it --rm -v "$(pwd)/test.php:/home/test.php" php:latest /bin/bash 