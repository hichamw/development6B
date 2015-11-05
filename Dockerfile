# A basic apache server. To use either add or bind mount content under /var/www
FROM ubuntu

MAINTAINER Giz version: 0.1

#link local map with server map
ADD ./ /var/www
RUN apt-get update && apt-get install -y apache2 && apt-get clean && rm -rf /var/lib/apt/lists/*

#RUN apt-get install -y python-software-properties 
#
#ENV APACHE_RUN_USER www-data
#ENV APACHE_RUN_GROUP www-data
#ENV APACHE_LOG_DIR /var/log/apache2
#
#EXPOSE 80
#
#CMD ["/usr/sbin/apache2", "-D", "FOREGROUND"]