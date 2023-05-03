
INSERT INTO plugin (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
	tenant_pk, entitlement, entitlement_type, base_script, description, license, name, post_script, restart, total_stars, version, owner_id,
	tenant_id, git_checksum, git_id, git_url, git_public_visible, reference_id)
VALUES ('40288185617bce8101617bd19fd60002', 'admin', '2018-02-11 16:48:19.751', 'DCHQ SYSTEM', '2018-02-11 16:50:52.068', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4f3545f2-922e-408a-aeb7-58689f407b04', '402881834d9ee4d1014d9ee5d73f0010', 'PUBLIC', 'PUBLIC',
'#!/bin/bash


PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
#sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-apache
FILE="/home/kkp/site.pp"
/bin/cat <<EOM >$FILE
class { ''apache'':
  default_vhost => true,
}
EOM
sudo -i puppet apply $FILE', 'Puppet Apache', 'EULA', 'Puppet Apache', NULL, NULL, 1, '1.0', '402881834d9ee4d1014d9ee5d73f0014',
'402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, false, 'w9M91');

INSERT INTO  plugin_envs (plugin_id, prop, val, hidden, eval)
values ('40288185617bce8101617bd19fd60002', 'servers', 'server {{AppServer | container_hostname}}:8080;', false, null);

INSERT INTO plugin (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
	tenant_pk, entitlement, entitlement_type, base_script, description, license, name, post_script, restart, total_stars, version, owner_id,
	tenant_id, git_checksum, git_id, git_url, git_public_visible, reference_id)
VALUES ('40288185617bce9101617bd5225b0007', 'admin', '2018-02-11 16:48:19.751', 'DCHQ SYSTEM', '2018-02-11 16:50:52.068', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4f3545f2-882e-408a-aeb7-58689f407b04', '402881834d9ee4d1014d9ee5d73f0010', 'PUBLIC', 'PUBLIC',
'#!/bin/bash
PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
#sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-apache
FILE="/home/kkp/site.pp"
/bin/cat <<EOM >$FILE
class { ''apache'':
  default_vhost => true,
}
EOM
sudo -i puppet apply $FILE'', ''Puppet Apache'', ''EULA'', ''Puppet Apache'', NULL, NULL, 1, ''1.0'', ''402881834d9ee4d1014d9ee5d73f0014'',
''402881834d9ee4d1014d9ee5d73f0010'', NULL, NULL, NULL, false, ''w9M91'');

INSERT INTO  plugin_envs (plugin_id, prop, val, hidden, eval)
values (''40288185617bce8101617bd19fd60002'', ''servers'', ''server {{AppServer | container_hostname}}:8080;'', false, null);

INSERT INTO plugin (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, base_script, description, license, name, post_script, restart, total_stars, version, owner_id,
  tenant_id, git_checksum, git_id, git_url, git_public_visible, reference_id)
VALUES (''40288185617bce9101617bd5225b0007'', ''admin'', ''2018-02-11 16:48:19.751'', ''DCHQ SYSTEM'', ''2018-02-11 16:50:52.068'', false, false, 1,
''402881834d9ee4d1014d9ee5d73f0014'', ''4f3545f2-882e-408a-aeb7-58689f407b04'', ''402881834d9ee4d1014d9ee5d73f0010'', ''PUBLIC'', ''PUBLIC'',
''#!/bin/bash

PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
#sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-mysql
sudo -i puppet module install puppetlabs-apache
sudo -i puppet module install puppetlabs-postgresql
sudo -i puppet module install puppetlabs-tomcat
FILE="/home/kkp/site.pp"
/bin/cat <<EOM >$FILE
class { ''java'': }

tomcat::install { ''/opt/tomcat9'':
  source_url => ''https://www.apache.org/dist/tomcat/tomcat-9/v9.0.x/bin/apache-tomcat-9.0.x.tar.gz''
}
tomcat::instance { ''tomcat9-second'':
  catalina_home => ''/opt/tomcat9'',
  catalina_base => ''/opt/tomcat9/second'',
}
# Change the default port of the second instance server and HTTP connector
tomcat::config::server { ''tomcat9-second'':
  catalina_base => ''/opt/tomcat9/second'',
  port          => ''8006'',
}
tomcat::config::server::connector { ''tomcat9-second-http'':
  catalina_base         => ''/opt/tomcat9/second'',
  port                  => ''8081'',
  protocol              => ''HTTP/1.1'',
  additional_attributes => {
    ''redirectPort'' => ''8443''
  },
}
tomcat::war { ''sample.war'':
  catalina_base => ''/opt/tomcat9/second'',
  war_source    => ''/opt/tomcat9/webapps/docs/appdev/sample/sample.war'',
}

EOM
sudo -i puppet apply $FILE
', 'Puppet Tomcat', 'EULA', 'Puppet Tomcat', NULL, NULL, 1, '1.0', '402881834d9ee4d1014d9ee5d73f0014',
'402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, false, 'E3LGN');

INSERT INTO  plugin_envs (plugin_id, prop, val, hidden, eval)
values ('40288185617bce9101617bd5225b0007', 'servers', 'server {{AppServer | container_hostname}}:8080;', false, null);

INSERT INTO plugin (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
	tenant_pk, entitlement, entitlement_type, base_script, description, license, name, post_script, restart, total_stars, version, owner_id,
	tenant_id, git_checksum, git_id, git_url, git_public_visible, reference_id)
VALUES ('40988185617bce8101617bd3264d0004', 'admin', '2018-02-11 16:48:19.751', 'DCHQ SYSTEM', '2018-02-11 16:50:52.068', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4f3545f2-982e-408a-aeb7-58689f407b04', '402881834d9ee4d1014d9ee5d73f0010', 'PUBLIC', 'PUBLIC',
'#!/bin/bash


PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
#sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
puppet module install dwerder-mongodb
FILE="/home/kkp/site.pp"
/bin/cat <<EOM >$FILE
# mongodb 2.6.x
class { ''mongodb'':
 package_name   => ''mongodb-org'',
 package_ensure => ''2.6.2-1'',
 logdir         => ''/var/log/mongodb/'',
 # only debian like distros
 old_servicename => ''mongod''
}
class { ''mongodb'':
  run_as_user  => mongod,
  run_as_group => wheel,
  logdir       => ''/nfsshare/mymongologs/''
}
mongodb::mongod {
  ''my_mongod_instanceX'':
    mongod_instance    => ''mongodb1'',
    mongod_replSet     => ''mongoShard1'',
    mongod_add_options => [''slowms = 20'']
}
EOM
sudo -i puppet apply $FILE', 'Puppet-Mongo', 'EULA', 'Puppet-Mongo', NULL, NULL, 1, '1.0', '402881834d9ee4d1014d9ee5d73f0014',
'402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, false, 'QA67s');

INSERT INTO  plugin_envs (plugin_id, prop, val, hidden, eval)
values ('40988185617bce8101617bd3264d0004', 'servers', 'server {{AppServer | container_hostname}}:8080;', false, null);

INSERT INTO plugin (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
	tenant_pk, entitlement, entitlement_type, base_script, description, license, name, post_script, restart, total_stars, version, owner_id,
	tenant_id, git_checksum, git_id, git_url, git_public_visible, reference_id)
VALUES ('40299185617bce8101617bd434840006', 'admin', '2018-02-11 16:48:19.751', 'DCHQ SYSTEM', '2018-02-11 16:50:52.068', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4f3545f2-672e-408a-aeb7-58689f407b04', '402881834d9ee4d1014d9ee5d73f0010', 'PUBLIC', 'PUBLIC',
'#!/bin/bash

PUPPET_AGENT_DEB="https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb"
wget https://apt.puppetlabs.com/puppetlabs-release-pc1-xenial.deb
sudo dpkg -i puppetlabs-release-pc1-xenial.deb
#sudo apt update && sudo apt -y install puppet-agent
echo "export PATH=/opt/puppetlabs/bin:$PATH"
sudo -i puppet module install puppetlabs-mysql
sudo -i puppet module install puppetlabs-apache
sudo -i puppet module install puppetlabs-postgresql
FILE="/home/kkp/site.pp"
/bin/cat <<EOM >$FILE
class { ''postgresql::server'':
  postgres_password          => ''root123'',
}

postgresql::server::db { ''mydatabasename'':
  user     => ''mydatabaseuser'',
  password => postgresql_password(''mydatabaseuser'', ''mypassword''),
}
# rule for remote connections
postgresql::server::pg_hba_rule { ''allow remote connections with password'':
  type        => ''host'',
  database    => ''all'',
  user        => ''all'',
  address     => ''all'',
  auth_method => ''md5'',
}
EOM
sudo -i puppet apply $FILE', 'Puppet Postgres', 'EULA', 'Puppet Postgres', NULL, NULL, 1, '1.0', '402881834d9ee4d1014d9ee5d73f0014',
'402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, false, 'DSOAr');

INSERT INTO  plugin_envs (plugin_id, prop, val, hidden, eval)
values ('40299185617bce8101617bd434840006', 'servers', 'server {{AppServer | container_hostname}}:8080;', false, null);
