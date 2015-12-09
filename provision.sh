#!/usr/bin/env bash
export DEBIAN_FRONTEND=noninteractive

echo "Europe/Amsterdam" > /etc/timezone
sudo dpkg-reconfigure -f noninteractive tzdata

sudo apt-get -y update

# Force a blank root password for mysql
echo "mysql-server mysql-server/root_password password hafahtjappie123" | debconf-set-selections
echo "mysql-server mysql-server/root_password_again password hafahtjappie123" | debconf-set-selections

sudo apt-get -y install mysql-server-5.6
sudo apt-get install software-properties-common python-software-properties
sudo add-apt-repository ppa:fkrull/deadsnakes
sudo add-apt-repository ppa:webupd8team/java

sudo apt-get -y update

sudo apt-get -y install python2.7
sudo apt-get -y install apache2
sudo apt-get -y install git-core gcc autoconf make
sudo apt-get -y install oracle-java8-installer
sudo apt-get -y install maven

sudo rm /etc/apache2/sites-available/000-default.conf
sudo touch /etc/apache2/sites-available/000-default.conf
sudo cat <<'EOT' > /etc/apache2/sites-available/000-default.conf
<VirtualHost *:80>
        # The ServerName directive sets the request scheme, hostname and port that
        # the server uses to identify itself. This is used when creating
        # redirection URLs. In the context of virtual hosts, the ServerName
        # specifies what hostname must appear in the request's Host: header to
        # match this virtual host. For the default virtual host (this file) this
        # value is not decisive as it is used as a last resort host regardless.
        # However, you must set it for any further virtual host explicitly.
        #ServerName www.example.com

        ServerAdmin webmaster@localhost
        DocumentRoot /var/www/giz/www/

        # Available loglevels: trace8, ..., trace1, debug, info, notice, warn,
        # error, crit, alert, emerg.
        # It is also possible to configure the loglevel for particular
        # modules, e.g.
        #LogLevel info ssl:warn

        ErrorLog ${APACHE_LOG_DIR}/error.log
        CustomLog ${APACHE_LOG_DIR}/access.log combined

        # For most configuration files from conf-available/, which are
        # enabled or disabled at a global level, it is possible to
        # include a line for only one particular virtual host. For example the
        # following line enables the CGI configuration for this host only
        # after it has been globally disabled with "a2disconf".
        #Include conf-available/serve-cgi-bin.conf
</VirtualHost>

EOT

sudo rm -rf /var/www/html/index.html
sudo rm -rf /var/www/html

sudo service apache2 restart
