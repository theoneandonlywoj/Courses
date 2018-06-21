FROM ubuntu:latest

# Update and upgrade the system
RUN apt-get update && apt-get upgrade -y

# Install Nano using apt-get install
# Don't forget to add -y to agree for the installation
# Exclude additional, generally recommended libraries
# Remove any temporary files with apt-get clean
RUN apt-get install -y --no-install-recommends nano && apt-get clean -y 