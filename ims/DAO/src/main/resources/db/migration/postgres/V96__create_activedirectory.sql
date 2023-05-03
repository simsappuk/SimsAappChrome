-- Table: acitve_directory

-- DROP TABLE ldap;

CREATE TABLE ldap
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
  admindn character varying(255),
  base character varying(255),
  name character varying(255),
  password text,
  url character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT ldap_pkey PRIMARY KEY (id),
  CONSTRAINT fk_t7o62qiua4k0hygh5sh8oxg7w FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tn2dgtowp25gf9rei0vh33f3g FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ldap
  OWNER TO dchq;
