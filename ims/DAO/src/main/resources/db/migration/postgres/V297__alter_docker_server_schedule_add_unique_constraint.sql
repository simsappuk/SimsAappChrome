--docker server id as unique constraint, a docker server can have only one schedule.
ALTER TABLE docker_server_schedule ADD CONSTRAINT docker_server_id_uk UNIQUE (docker_server_id);
