-- author Mohammed Shoukath Ali
-- alter TABLE datacenter;
-- add column machine compose

ALTER TABLE data_center ADD COLUMN machine_compose character varying(255);
ALTER TABLE data_center ADD COLUMN pool_limit integer;