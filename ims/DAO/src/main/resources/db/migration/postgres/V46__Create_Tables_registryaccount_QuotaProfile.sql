  -- Table: registry_account_quota_profile_pks

-- DROP TABLE registry_account_quota_profile_pks;

CREATE TABLE registry_account_quota_profile_pks
(
  registry_account_id character varying(255) NOT NULL,
  quota_profile_pks character varying(255) NOT NULL,
  CONSTRAINT registry_account_quota_profiles_pkey PRIMARY KEY (registry_account_id, quota_profile_pks),
  CONSTRAINT fk_quota_profile_pks FOREIGN KEY (quota_profile_pks)
      REFERENCES quota_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_registry_account_qp FOREIGN KEY (registry_account_id)
      REFERENCES registry_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE registry_account_quota_profile_pks
  OWNER TO dchq;

  
-- Table: registry_account_quota_profiles

-- DROP TABLE registry_account_quota_profiles;

CREATE TABLE registry_account_quota_profiles
(
  registry_account_id character varying(255) NOT NULL,
  quota_profiles_id character varying(255) NOT NULL,
  CONSTRAINT registry_account_quota_profiles_prkey PRIMARY KEY (registry_account_id, quota_profiles_id),
  CONSTRAINT fk_bp_registry_account_id FOREIGN KEY (registry_account_id)
      REFERENCES registry_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_registry_account_quota_profiles FOREIGN KEY (quota_profiles_id)
      REFERENCES quota_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE registry_account_quota_profiles
  OWNER TO dchq;

  
