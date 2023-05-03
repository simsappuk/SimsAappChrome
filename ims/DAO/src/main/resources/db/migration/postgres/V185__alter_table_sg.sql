-- Author: Mohammed Shoukath Ali
-- Table: network_acl


ALTER TABLE security_group drop  COLUMN  if exists firewal_sg_name;
ALTER TABLE security_group add COLUMN firewal_sg_name character varying(255);





