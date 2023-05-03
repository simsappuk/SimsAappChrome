-- author Mohammed Shoukath Ali
-- alter TABLE docker_server;
-- add column machine compose

ALTER TABLE docker_server ADD COLUMN machine_compose character varying(255);
