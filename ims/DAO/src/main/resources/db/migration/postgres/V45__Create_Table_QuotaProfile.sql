-- Table: quota_profile

-- DROP TABLE quota_profile;

CREATE TABLE quota_profile
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
  currency character varying(255),
  name character varying(255),
  quota_entitlement_type character varying(255),
  quota_type character varying(255),
  quota_value double precision,
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT quota_profile_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE quota_profile
  OWNER TO dchq;
