-- Table: docker_server

-- Adding a new Column subnet_id
-- Adding a new Column subnet_name

ALTER TABLE docker_server ADD COLUMN subnet_id character varying(255);
ALTER TABLE docker_server ADD COLUMN subnet_name character varying(255);
