INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
  owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars,
  user_name, version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable,
  short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90101', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90101', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Tomcat – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010',
    NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Tomcat Mysql', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90102', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90102', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Jetty – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Jetty MySQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90103', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90103', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – JBoss – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx JBoss MySQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid, tenant_pk,
  entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id, tenant_id,
  image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90104', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90104', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '2-Tier Java (WebSphere – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'AppServer:
  image: websphere-liberty:webProfile6
  publish_all: true
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
    - LICENSE=accept
  plugins:
    - !plugin
      id: rPuVb
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect.war
        - delete_dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '2-tier single host - WebSphere MySQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90105', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90105', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Tomcat – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Tomcat PostgreSQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90106', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90106', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Jetty – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Jetty PostgreSQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90107', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90107', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – JBoss – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx JBoss PostgreSQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90108', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90108', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '2-Tier Java (WebSphere – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'AppServer:
  image: websphere-liberty:webProfile6
  publish_all: true
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
    - LICENSE=accept
  plugins:
    - !plugin
      id: rPuVb
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect.war
        - delete_dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '2-tier single host - WebSphere PostgreSQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90109', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90109', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Tomcat – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
    - TZ=UTC
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Tomcat Oracle-XE', NULL);






INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90110', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90110', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Jetty – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Jetty Oracle-XE', NULL);






INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90111', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90111', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – JBoss – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx JBoss Oracle-XE', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90112', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90112', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '2-Tier Java (WebSphere – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'AppServer:
  image: websphere-liberty:webProfile6
  publish_all: true
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
    - LICENSE=accept
  plugins:
    - !plugin
      id: rPuVb
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect.war
        - delete_dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '2-tier single host - WebSphere Oracle-XE', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90113', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90113', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Tomcat – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Tomcat MariaDB', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90114', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90114', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – Jetty – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx Jetty MariaDB', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90115', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90115', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (Nginx – JBoss – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'LB:
  image: nginx:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: 0H1Nk
      restart: true
      arguments:
        - servers=server {{AppServer | container_ip}}:8080;
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - Nginx JBoss MariaDB', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90116', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90116', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '2-Tier Java (WebSphere – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'AppServer:
  image: websphere-liberty:webProfile6
  publish_all: true
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
    - LICENSE=accept
  plugins:
    - !plugin
      id: rPuVb
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect.war
        - delete_dir=/opt/ibm/wlp/usr/servers/defaultServer/dropins/dbconnect
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '2-tier single host - WebSphere MariaDB', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90117', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90117', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Tomcat – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Tomcat MySQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90118', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90118', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Jetty – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Jetty MySQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90119', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90119', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – JBoss – MySQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MySQL|container_ip}}:3306/{{MySQL|MYSQL_DATABASE}}
    - database_username={{MySQL|MYSQL_USER}}
    - database_password={{MySQL|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
MySQL:
  image: mysql:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP JBoss MySQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90120', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90120', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Tomcat – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Tomcat PostgreSQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90121', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90121', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Jetty – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Jetty PostgreSQL', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90122', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90122', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – JBoss – PostgreSQL)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=org.postgresql.Driver
    - database_url=jdbc:postgresql://{{Postgres|container_ip}}:5432/{{Postgres|POSTGRES_DB}}
    - database_username={{Postgres|POSTGRES_USER}}
    - database_password={{Postgres|POSTGRES_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
Postgres:
  image: postgres:latest
  host: host1
  mem_min: 400m
  environment:
    - POSTGRES_USER=root
    - POSTGRES_PASSWORD={{alphanumeric | 8}}
    - POSTGRES_DB=names', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP JBoss PostgreSQL', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90123', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90123', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Tomcat – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
    - TZ=UTC
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Tomcat Oracle-XE', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90124', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90124', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Jetty – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Jetty Oracle-XE', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
  tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
  tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90125', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90125', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – JBoss – Oracle-XE)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=oracle.jdbc.OracleDriver
    - database_url=jdbc:oracle:thin:@//{{Oracle|container_ip}}:1521/{{Oracle|sid}}
    - database_username={{Oracle|username}}
    - database_password={{Oracle|password}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
Oracle:
  image: wnameless/oracle-xe-11g:latest
  host: host1
  mem_min: 400m
  environment:
    - username=system
    - password=oracle
    - sid=xe', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP JBoss Oracle-XE', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90126', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90126', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Tomcat – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: tomcat:8.0.21-jre8
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/usr/local/tomcat/webapps/ROOT.war
        - delete_dir=/usr/local/tomcat/webapps/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Tomcat MariaDB', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90127', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90127', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – Jetty – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jetty:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/var/lib/jetty/webapps/ROOT.war
        - delete_dir=/var/lib/jetty/webapps/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP Jetty MariaDB', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('402881864e1a36cc014e1a399cf90128', 'admin', '2015-12-09 00:44:20.217', 'admin', '2015-12-09 00:44:20.217', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '402881864e1a36cc014e1a399cf90128', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'DOCKER_COMPOSE', NULL, '3-Tier Java (ApacheHTTP – JBoss – MariaDB)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '2.0', 'HTTP-LB:
  image: httpd:latest
  publish_all: true
  mem_min: 50m
  host: host1
  plugins:
    - !plugin
      id: uazUi
      restart: true
      arguments:
        - BalancerMembers=BalancerMember http://{{AppServer | container_ip}}:8080
AppServer:
  image: jboss/wildfly:latest
  mem_min: 600m
  host: host1
  cluster_size: 1
  environment:
    - database_driverClassName=com.mysql.jdbc.Driver
    - database_url=jdbc:mysql://{{MariaDB|container_ip}}:3306/{{MariaDB|MYSQL_DATABASE}}
    - database_username={{MariaDB|MYSQL_USER}}
    - database_password={{MariaDB|MYSQL_ROOT_PASSWORD}}
  plugins:
    - !plugin
      id: oncXN
      restart: true
      arguments:
        - file_url=https://github.com/dchqinc/dchq-docker-java-example/raw/master/dbconnect.war
        - dir=/opt/jboss/wildfly/standalone/deployments/ROOT.war
        - delete_dir=/opt/jboss/wildfly/standalone/deployments/ROOT
MariaDB:
  image: mariadb:latest
  host: host1
  mem_min: 400m
  environment:
    - MYSQL_USER=root
    - MYSQL_DATABASE=names
    - MYSQL_ROOT_PASSWORD={{alphanumeric|8}}', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, true, '3-tier single host - ApacheHTTP JBoss MariaDB', NULL);

