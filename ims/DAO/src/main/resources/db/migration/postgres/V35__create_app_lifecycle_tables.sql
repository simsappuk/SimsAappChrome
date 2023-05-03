-- Table: life_cycle

-- DROP TABLE life_cycle;

CREATE TABLE life_cycle
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
  CONSTRAINT life_cycle_pkey PRIMARY KEY (id),
  CONSTRAINT fk_myapn5du2th0iv52u6af2jvye FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE life_cycle
  OWNER TO dchq;



-- Table: node_profile

-- DROP TABLE node_profile;

CREATE TABLE node_profile
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
  name character varying(255),
  owner_id character varying(255),
  CONSTRAINT node_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_6yrxs9dwbl9m5of9p7uhu4ewq FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE node_profile
  OWNER TO dchq;


-- Table: life_cycle_node_profiles

-- DROP TABLE life_cycle_node_profiles;

CREATE TABLE life_cycle_node_profiles
(
  life_cycle_id character varying(255) NOT NULL,
  node_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_m11vqo166om807v188qapen9l FOREIGN KEY (node_profiles_id)
      REFERENCES node_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ph7koem493ta4cuuuwd3j1wp6 FOREIGN KEY (life_cycle_id)
      REFERENCES life_cycle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_m11vqo166om807v188qapen9l UNIQUE (node_profiles_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE life_cycle_node_profiles
  OWNER TO dchq;


-- Table: node_profile_plugin_profiles

-- DROP TABLE node_profile_plugin_profiles;

CREATE TABLE node_profile_plugin_profiles
(
  node_profile_id character varying(255) NOT NULL,
  plugin_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_77t4uaro06ru7w41p4lh5mrq1 FOREIGN KEY (plugin_profiles_id)
      REFERENCES plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sawjleoydpq92vkcsan5gc2ur FOREIGN KEY (node_profile_id)
      REFERENCES node_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_77t4uaro06ru7w41p4lh5mrq1 UNIQUE (plugin_profiles_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE node_profile_plugin_profiles
  OWNER TO dchq;
