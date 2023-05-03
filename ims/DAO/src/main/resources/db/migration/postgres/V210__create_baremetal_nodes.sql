-- Table: baremetal_nodes

-- DROP TABLE baremetal_nodes;

CREATE TABLE baremetal_nodes
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  uuid character varying(255),
  provider_id character varying(255),
  ip character varying(255),
  used_by_id character varying(255),
  status character varying(50),
  owner_id character varying(255),
  owner_pk character varying(255),
  name character varying(255),
  username character varying(255),
  password character varying(255),
  CONSTRAINT ppk1 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE baremetal_nodes
  OWNER TO dchq;

