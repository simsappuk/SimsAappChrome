CREATE TABLE docker_server_usage_hourly (
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
    docker_server_id character varying(255) NOT NULL,
    cpu_usage character varying(255),
    mem_usage character varying(255),
    disk_usage character varying(255),
    owner_id character varying(255)
);

ALTER TABLE docker_server_usage_hourly OWNER TO dchq;
