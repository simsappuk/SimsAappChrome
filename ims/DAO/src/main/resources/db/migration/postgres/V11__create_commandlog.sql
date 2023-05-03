-- Table: command_log

-- DROP TABLE command_log;

CREATE TABLE command_log
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  command character varying(255),
  executed_by character varying(255),
  response text,
  container_id character varying(255),
  secondary_id character varying(255),
  CONSTRAINT command_log_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE command_log
  OWNER TO dchq;
