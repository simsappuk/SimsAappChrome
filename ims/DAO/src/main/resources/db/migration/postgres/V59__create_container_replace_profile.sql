
-- Table: container_profile

-- DROP TABLE container_profile;

CREATE TABLE container_profile
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
  provision character varying(255),
  owner_id character varying(255),
  CONSTRAINT container_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_fc5l5oovhx4rvy0m2gew4x5ex FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE container_profile
  OWNER TO dchq;

  
  -- Table: container_replace

-- DROP TABLE container_replace;

CREATE TABLE container_replace
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
  active boolean,
  container_id character varying(255),
  endpoint character varying(255),
  image character varying(255),
  name character varying(255),
  next_cron_date timestamp without time zone,
  time_ character varying(255),
  owner_id character varying(255),
  cron_active boolean,
  on_new_image boolean,
  CONSTRAINT container_replace_pkey PRIMARY KEY (id),
  CONSTRAINT fk_d4caf0a721ejo4ft5i5gv1d1m FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE container_replace
  OWNER TO dchq;

  
  -- Table: container_profile_rep_prof

-- DROP TABLE container_profile_rep_prof;

CREATE TABLE container_profile_rep_prof
(
  container_profile_id character varying(255) NOT NULL,
  rep_prof_id character varying(255) NOT NULL,
  CONSTRAINT fk_iwo2istl2hofifbf59f6ctetb FOREIGN KEY (rep_prof_id)
      REFERENCES container_replace (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_o6awjl3wjlwkljvq5sqylfg3k FOREIGN KEY (container_profile_id)
      REFERENCES container_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_iwo2istl2hofifbf59f6ctetb UNIQUE (rep_prof_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE container_profile_rep_prof
  OWNER TO dchq;
