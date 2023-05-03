
-- Author Mohammed Luqman Shareef

-- Table: machine_type

-- DROP TABLE machine_type;

CREATE TABLE machine_type
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
  entitlement character varying(255),
  entitlement_type character varying(255),
  name character varying(255),
  cloud character varying(255),
  machine_id character varying(255),
  vcpu character varying(255),
  mem character varying(255),
  disk character varying(255),
  region character varying(255),
  cost character varying(255),
  disk_type character varying(255),
  owner_id character varying(255),
  CONSTRAINT machine_type_pkey PRIMARY KEY (id)
);