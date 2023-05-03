CREATE TABLE docker_server_tags (
    docker_server_id character varying(255) NOT NULL,
    tags_id character varying(255) NOT NULL
);

ALTER TABLE docker_server_tags OWNER TO dchq;

ALTER TABLE ONLY docker_server_tags
    ADD CONSTRAINT uk_eursd4fad9babies48dv90jdw UNIQUE (tags_id);

CREATE TABLE tags (
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
    tag_name character varying(255),
    tag_value character varying(255),
    tag_type character varying(255),
    owner_id character varying(255)
);

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);

ALTER TABLE tags OWNER TO dchq;

ALTER TABLE ONLY docker_server_tags
    ADD CONSTRAINT fk_de69owltyal0fork80fowl4e FOREIGN KEY (tags_id) REFERENCES tags(id);

ALTER TABLE ONLY docker_server_tags
    ADD CONSTRAINT fk_jpasd4fad9babies48dv90jvv FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);