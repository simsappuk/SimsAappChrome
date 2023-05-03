-- author Faizan Mohammed
-- Create Tables for Object Storage
-- Table: object_storage, object_storage_entitled_users, object_storage_entitled_users_pks, object_storage_entitled_users_pks, object_storage_entitled_orgs
-- object_storage_entitled_user_groups, object_storage_entitled_groups_pks,


CREATE TABLE object_storage
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
  uuid character varying(255),
  tenant_pk character varying(255),
  entitlement character varying(255),
  entitlement_type character varying(255),
  name character varying(255),
  object_storage_id character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  status character varying(255),
  parent character varying(255),
  driver character varying(255),
  CONSTRAINT object_storage_pkey PRIMARY KEY (id),
  CONSTRAINT fk_object_storage_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_object_storage_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE object_storage_entitled_users
(
  object_storage_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT object_storage_entitled_users_pkey PRIMARY KEY (object_storage_id, entitled_users_id),
  CONSTRAINT fk_object_storage_entitled_users_id FOREIGN KEY (object_storage_id)
      REFERENCES object_storage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT object_storage_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE object_storage_entitled_users_pks
(
  object_storage_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT object_storage_entitled_users_pks_netid FOREIGN KEY (object_storage_id)
      REFERENCES object_storage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE object_storage_entitled_user_groups
(
  object_storage_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT object_storage_entitled_user_groups_pkey PRIMARY KEY (object_storage_id, entitled_user_groups_id),
  CONSTRAINT fk_object_storage_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_object_storage_entitled_user_groups_netid FOREIGN KEY (object_storage_id)
      REFERENCES object_storage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE object_storage_entitled_groups_pks
(
  object_storage_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_object_storage_entitled_groups_pks_netid FOREIGN KEY (object_storage_id)
      REFERENCES object_storage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE object_storage_entitled_orgs
(
  object_storage_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT object_storage_entitled_orgs_pkey PRIMARY KEY (object_storage_id, entitled_orgs_id),
  CONSTRAINT fk_object_storage_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_object_storage_entitled_orgs_netid FOREIGN KEY (object_storage_id)
      REFERENCES object_storage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE object_storage_task (
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
    object_storage_task_status character varying(255),
    object_storage_task_type character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    waiting_on character varying(255),
    owner_id character varying(255),
    object_storage_id character varying(255)
);

create table object_storage_task_dynamic_attributes (
	docker_server_task_id character varying(255),
	dynamic_attributes character varying(255),
	dynamic_attributes_key character varying(255)
);