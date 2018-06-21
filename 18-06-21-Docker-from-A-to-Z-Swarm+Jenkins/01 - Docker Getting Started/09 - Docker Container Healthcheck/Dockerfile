FROM httpd:latest

RUN apt-get update && apt-get install -y --no-install-recommends curl && apt-get clean

# Httpd uses port 80
EXPOSE 80

# The healthcheck command is run on the localhost (inside the docker container!)
HEALTHCHECK --interval=15s --retries=5 --timeout=30s --start-period=30s CMD curl --head -f "localhost:80" || exit 1