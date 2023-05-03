-- Table: code_exec_version

-- DROP TABLE code_exec_version;

CREATE TABLE code_exec_version
(
  version integer NOT NULL,
  description character varying(255),
  bean character varying(255) NOT NULL,
  method character varying(255) NOT NULL,
  params character varying(255),
  status character varying(50),
  interface_fqn character varying(255) NOT NULL,
  checksum integer,
  logs text,
  start_time timestamp without time zone,
  time_out integer,
  end_time timestamp without time zone,
  CONSTRAINT code_exec_version_version_pkey PRIMARY KEY (version)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE code_exec_version
  OWNER TO dchq;
