-- Table: docker_server

-- UPDATE Column virtual_app_name

ALTER TABLE docker_server ADD COLUMN virtual_app_id character varying(255);
ALTER TABLE docker_server ADD COLUMN virtual_app boolean;


