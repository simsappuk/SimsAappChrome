-- Author: Intesar Mohammed

-- Table: iso_template

 DROP TABLE iso_template;

CREATE TABLE iso_template
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
  owner_id character varying(255),
  owner_pk character varying(255),
  name  character varying(255),
  url  character varying(255),
  active  character varying(255),
  CONSTRAINT iso_template_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iso_template
  OWNER TO dchq;
