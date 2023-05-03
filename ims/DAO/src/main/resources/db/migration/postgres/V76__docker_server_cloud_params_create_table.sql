-- Table: docker_server_cloud_params
-- author Mohammed Luqman Shareef

create table docker_server_cloud_params(
	docker_server_id character varying(255),
	cloud_params character varying(255),
	cloud_params_key character varying(255)
);

ALTER TABLE docker_server_cloud_params
  OWNER TO dchq;

