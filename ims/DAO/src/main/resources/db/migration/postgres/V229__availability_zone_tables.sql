-- Author: Mohammed Luqman Shareef

-- Table: availability_zone

-- DROP TABLE availability_zone;

CREATE TABLE availability_zone
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
  owner_id character varying(255),
  provider_id character varying(255),
  cluster character varying(255),
  region character varying(255),
  url character varying(255),
  user_name character varying(255),
  password character varying(255),
  
  CONSTRAINT availability_zone_pkey PRIMARY KEY (id)
);

