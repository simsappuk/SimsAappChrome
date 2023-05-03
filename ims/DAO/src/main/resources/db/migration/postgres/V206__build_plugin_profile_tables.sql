-- Create build pluginprofile tables

-- Table: build_plugin_profile

--

CREATE TABLE build_plugin_profile
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
  build_id character varying(255),
  build_name character varying(255),
  cron character varying(255),
  next_cron_date timestamp without time zone,
  owner_id character varying(255),
  CONSTRAINT build_plugin_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_n5w7ohoit4x845grqnqxxxxri FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



-- Table: build_plugin_profile_plugin_profiles

--

CREATE TABLE build_plugin_profile_plugin_profiles
(
  build_plugin_profile_id character varying(255) NOT NULL,
  plugin_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_1ath3hud5q7wtf8a7ofts6ebs FOREIGN KEY (plugin_profiles_id)
      REFERENCES plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_gu06fsxsiaikdylk9d3354x7d FOREIGN KEY (build_plugin_profile_id)
      REFERENCES build_plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_1ath3hud5q7wtf8a7ofts6ebs UNIQUE (plugin_profiles_id)
);



