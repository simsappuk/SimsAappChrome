
-- Author Mohammed Luqman Shareef
-- Table: cloud_regions

-- DROP TABLE cloud_regions;

CREATE TABLE cloud_regions
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
  description character varying(255),
  owner_id character varying(255),
  CONSTRAINT cloud_regions_pkey PRIMARY KEY (id)
);