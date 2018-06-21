FROM nginx:latest

RUN mkdir /etc/nginx/logs && touch /etc/nginx/logs/static.log

ADD ./nginx.conf /etc/nginx/conf.d/default.conf
ADD ./build /www

EXPOSE 80

CMD nginx -g "daemon off;" 