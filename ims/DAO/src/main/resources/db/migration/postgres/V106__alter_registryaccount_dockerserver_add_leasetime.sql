-- Table: registry_account and docker_server
-- author Mohammed Shoukath Ali
-- Adding a new Column Lease time


ALTER TABLE registry_account ADD COLUMN lease_time character varying(100);
ALTER TABLE docker_server ADD COLUMN expiration_date timestamp without time zone;