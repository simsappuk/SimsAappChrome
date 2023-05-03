-- Add script lang and update entitlement of plugins made available by default

UPDATE plugin SET script_lang = 'SHELL' where id = '40288185617bce8101617bd19fd60002';
UPDATE plugin SET script_lang = 'SHELL' where id = '40288185617bce9101617bd5225b0007';
UPDATE plugin SET script_lang = 'SHELL' where id = '40988185617bce8101617bd3264d0004';
UPDATE plugin SET script_lang = 'SHELL' where id = '40299185617bce8101617bd434840006';

--- ADD entitlement

UPDATE plugin SET entitlement_type = 'GLOBAL' where id = '40288185617bce8101617bd19fd60002';
UPDATE plugin SET entitlement_type = 'GLOBAL' where id = '40288185617bce9101617bd5225b0007';
UPDATE plugin SET entitlement_type = 'GLOBAL' where id = '40988185617bce8101617bd3264d0004';
UPDATE plugin SET entitlement_type = 'GLOBAL' where id = '40299185617bce8101617bd434840006';

--- UPDATE reference id
UPDATE plugin SET reference_id = 'UKweA' where id = '40288185617bce8101617bd19fd60002';
UPDATE plugin SET reference_id = 'Be69y' where id = '40288185617bce9101617bd5225b0007';
UPDATE plugin SET reference_id = 'QA67s' where id = '40988185617bce8101617bd3264d0004';
UPDATE plugin SET reference_id = 'Yj5he' where id = '40299185617bce8101617bd434840006';

UPDATE plugin SET base_script = '#!/bin/bash

APP1_IP=`cat /home/ubuntu/app1_ip`
APP2_IP=`cat /home/ubuntu/app2_ip`
APP3_IP=`cat /home/ubuntu/app3_ip`

APP2_DB_IP="$APP_DB_IP"

APP3_WAR_URL=''https://www.dropbox.com/s/x0ewbc6dfj9cu5w/spring-jpa-postgresql-angularjs-0.0.1-SNAPSHOT.war?dl=1''
curl -L -o /home/ubuntu/app.war "$APP3_WAR_URL"
echo "$APP_WAR_URL" > /home/ubuntu/war_url

FILE="/home/ubuntu/tom.pp"
/bin/cat <<EOM >$FILE

tomcat::install { ''/opt/tomcat9'':
  source_url => ''https://www.apache.org/dist/tomcat/tomcat-9/v9.0.6/bin/apache-tomcat-9.0.6.tar.gz''
}
tomcat::instance { ''default'':
  catalina_home => ''/opt/tomcat9'',
  catalina_base => ''/opt/tomcat9'',
}
tomcat::setenv::entry { ''default'':
  config_file => ''/opt/tomcat9/bin/setenv.sh'',
  param          => ''CLASSPATH'',
  value		 => ''\$CLASSPATH:/home/ubuntu/''
}
#tomcat::setenv::entry { ''default'':
#  config_file => ''/opt/tomcat9/bin/setenv.sh'',
#  param          => ''JAVA_OPTS'',
#  value		 => ''\"\$JAVA_OPTS -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true\"''
#}
# Change the default port of the second instance server and HTTP connector
tomcat::config::server { ''default'':
  catalina_base => ''/opt/tomcat9'',
  port          => ''8181'',
}
tomcat::war { ''app.war'':
  catalina_base => ''/opt/tomcat9'',
  war_source    => ''/home/ubuntu/app.war'',
}


EOM

PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
sudo apt update && sudo apt -y install puppet-agent

echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-tomcat
sudo -i puppet apply $FILE
sleep 120
APP4_IP=`cat /home/ubuntu/app4_ip`
PROP_FILE="/home/ubuntu/application-myapp.properties"
/bin/cat <<EOM >$PROP_FILE
spring.datasource.url=jdbc:postgresql://APP2_DB_IP/test_sample
spring.datasource.username=postgres
spring.datasource.password=root123
spring.jpa.generate-ddl=true

spring.mvc.view.prefix = /WEB-INF/jsps/
spring.mvc.view.suffix = .jsp

EOM

sed -i "s/APP2_DB_IP/$APP4_IP/g" /home/ubuntu/application-myapp.properties
export CATALINA_BASE=/opt/tomcat9
export CATALINA_HOME=/opt/tomcat9
cd /opt/tomcat9
sleep 2
sudo $CATALINA_HOME/bin/catalina.sh stop
sleep 3
tom_pid=`ps -ef | grep tomcat | grep -v grep | grep -v restart | awk ''{print $2}''`
if [ ! -z "$tom_pid" -a "$tom_pid" != " " ]; then
        sudo kill -9 $tom_pid
fi
sleep 3
sudo $CATALINA_HOME/bin/catalina.sh start
' where id = '40288185617bce9101617bd5225b0007';

UPDATE plugin SET base_script = '#!/bin/bash

PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-mysql
sudo -i puppet module install puppetlabs-apache
sudo -i puppet module install puppetlabs-postgresql
FILE="/home/ubuntu/postgres.pp"
/bin/cat <<EOM >$FILE
class { ''postgresql::server'':
  postgres_password          => ''root123'',
}

postgresql::server::db { ''test_sample'':
  user     => ''test_user'',
  password => postgresql_password(''test_user'', ''test_pwd''),
}
postgresql::server::database_grant { ''test_user'':
  privilege => ''ALL'',
  db        => ''test_sample'',
  role        => ''test_user'',
}
postgresql::server::role { ''test_user'':
  superuser        => true,
}
# rule for remote connections
postgresql::server::pg_hba_rule { ''allow remote connections with password'':
  type        => ''host'',
  database    => ''all'',
  user        => ''all'',
  address     => ''all'',
  auth_method => ''md5'',
}
postgresql::server::config_entry { ''listen_addresses'':
  value => ''*'',
}
EOM
sudo -i puppet apply $FILE' where id = '40299185617bce8101617bd434840006';