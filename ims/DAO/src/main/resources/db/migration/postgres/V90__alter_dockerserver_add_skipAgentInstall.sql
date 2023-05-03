-- Table: docker_server

-- UPDATE Column skip_agent_install

ALTER TABLE docker_server ADD COLUMN skip_agent_install character varying(255);
