-- create table

CREATE TABLE entity_log
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  entity_id character varying(255),
  entity_type character varying(255),
  log text,
  secondary_id character varying(255),
  CONSTRAINT entity_log_pkey PRIMARY KEY (id)
)