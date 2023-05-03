-- author Mohammed Luqman Shareef
-- Create Tables for DockerNetworkTask


CREATE TABLE docker_network_task (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    docker_network_task_status character varying(255),
    docker_network_task_type character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    waiting_on character varying(255),
    owner_id character varying(255),
    docker_network_id character varying(255)
);

create table docker_network_task_dynamic_attributes (
	docker_server_task_id character varying(255),
	dynamic_attributes character varying(255),
	dynamic_attributes_key character varying(255)
);


