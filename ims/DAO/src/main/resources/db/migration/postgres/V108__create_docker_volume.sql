-- author Intesar Mohammed
-- Table: docker_volume, docker_volume_options, docker_volume_task, docker_volume_task_dynamic_attributes


CREATE TABLE docker_volume
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  owner_pk character varying(255),
  owner_id character varying(255),
  name character varying(255),
  uuid character varying(255),

  volume_id character varying(255),
  mount_point character varying(255),
  source character varying(255),
  destination character varying(255),
  containers character varying(255),
  scope character varying(255),
  driver character varying(255),

  mode character varying(255),
  size character varying(255),
  rw character varying(255),
  propagation character varying(255),

  created_on character varying(255),
  cluster_id character varying(255),
  mounted_on character varying(255),

  CONSTRAINT docker_volume_pkey PRIMARY KEY (id),
  CONSTRAINT fk_dv0nd87b2gp011ixqdk533iwb FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table docker_volume_options (
	docker_volume_id character varying(255),
	options character varying(255),
	options_key character varying(255)
);

CREATE TABLE docker_volume_task (
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
    docker_volume_task_status character varying(255),
    docker_volume_task_type character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    waiting_on character varying(255),
    owner_id character varying(255),
    docker_volume_id character varying(255)
);

create table docker_volume_task_dynamic_attributes (
	docker_server_task_id character varying(255),
	dynamic_attributes character varying(255),
	dynamic_attributes_key character varying(255)
);