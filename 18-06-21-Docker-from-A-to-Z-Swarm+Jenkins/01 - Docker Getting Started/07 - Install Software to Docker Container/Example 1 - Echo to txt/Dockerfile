FROM ubuntu:latest

# Runs the command and save it as a new Docker layer
# It will be saved as a layer
RUN echo "Test by Wojciech, 2018-06-03" > /home/test.txt

# This will be created as another layer
# Being startegic about how to use layers is important
# To balance reusability, creation speed and number of layers
RUN echo "Test test" > /home/test2.txt