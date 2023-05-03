-- author: Mohammed Luqman Shareef
-- table: docker_network
-- add new columns data_center_id, docker_server_id


ALTER TABLE docker_network ADD COLUMN data_center_id character varying(255);
ALTER TABLE docker_network ADD COLUMN docker_server_id character varying(255);

ALTER TABLE docker_network_task_dynamic_attributes RENAME COLUMN docker_server_task_id TO docker_network_task_id;

