# Base image
FROM nginx:latest

# Adding source
# You can use COPY as well
ADD index.html /www/

# Adding nginx configuration file
ADD nginx.conf /etc/nginx/conf.d/default.conf

# Commands below are already included with the Nginx base image

# Exposing ports of this container
# EXPOSE 80
# Expose HTTPS
# EXPOSE 443

# Command running on start-up
# Standard Nginx start-up command
# CMD nginx -g "deamon off;"
