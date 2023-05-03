-- Table: price_profile

-- DROP TABLE price_profile;

CREATE TABLE price_profile
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
  applicable_end_time timestamp without time zone,
  applicable_start_time timestamp without time zone,
  currency character varying(255),
  name character varying(255),
  price_unit character varying(255),
  value double precision,
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT price_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_2a415lfbpc7qmvx1sd5ipnwvj FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sd9xs4h9est11qpcpb9s33kj1 FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE price_profile
  OWNER TO dchq;
  
  
  -- Table: blueprint_price_profiles

-- DROP TABLE blueprint_price_profiles;

CREATE TABLE blueprint_price_profiles
(
  blueprint_id character varying(255) NOT NULL,
  price_profiles_id character varying(255) NOT NULL,
  CONSTRAINT blueprint_price_profiles_pkey PRIMARY KEY (blueprint_id, price_profiles_id),
  CONSTRAINT fk_83vuon7ie35o7f2yfc0mitpin FOREIGN KEY (price_profiles_id)
      REFERENCES price_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ebu4x79hnjwd08fp56jqlcum9 FOREIGN KEY (blueprint_id)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE blueprint_price_profiles
  OWNER TO dchq;

