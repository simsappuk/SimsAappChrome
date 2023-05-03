-- Table: blueprint
-- Author: Luqman Shareef
-- Adding new Column

alter table blueprint add column compose_version character varying(255);

-- Set old blue prints to version V1
update blueprint set compose_version = 'V1' where blueprint_type = 'DOCKER_COMPOSE' and  compose_version is null

