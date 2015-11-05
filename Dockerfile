# A basic apache server. To use either add or bind mount content under /var/www
FROM ubuntu:trusty

MAINTAINER Giz version: 0.1

RUN apt-get update
RUN apt-get upgrade -y

# Install Apache, MariaDB and PYTHON.
RUN apt-get update
RUN apt-get install -y python2.7
RUN apt-get install -y apache2
RUN \
  apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0xcbcb082a1bb943db && \
  echo "deb http://mariadb.mirror.iweb.com/repo/10.0/ubuntu `lsb_release -cs` main" > /etc/apt/sources.list.d/mariadb.list && \
  apt-get update && \
  DEBIAN_FRONTEND=noninteractive apt-get install -y mariadb-server && \
  rm -rf /var/lib/apt/lists/* && \
  sed -i 's/^\(bind-address\s.*\)/# \1/' /etc/mysql/my.cnf && \
  echo "mysqld_safe &" > /tmp/config && \
  echo "mysqladmin --silent --wait=30 ping || exit 1" >> /tmp/config && \
  echo "mysql -e 'GRANT ALL PRIVILEGES ON *.* TO \"root\"@\"%\" WITH GRANT OPTION;'" >> /tmp/config && \
  bash /tmp/config && \
  rm -f /tmp/config

# symlink
VOLUME ["/etc/mysql", "/etc/conf/", "/var/www", "/var/lib/mysql"]
ADD ./www /var/www

#start programs that are needed
RUN /etc/init.d/apache2 start

EXPOSE 80