-- Table: docker_server
-- author Mohammed Shoukath Ali

ALTER TABLE docker_server ADD COLUMN docker_server_plugin_profile character varying(255);

ALTER TABLE docker_server add column docker_server_plugin_profile_active character varying(255);

-- Table: docker_server_plugin_profile

-- DROP TABLE docker_server_plugin_profile;


CREATE TABLE docker_server_plugin_profile
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
  cron character varying(255),
  docker_server_id character varying(255),
  docker_server_name character varying(255),
  next_cron_date timestamp without time zone,
  owner_id character varying(255),
  CONSTRAINT docker_server_plugin_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_99pa8fxu35xpb8rdsajgk9obb FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  
-- Table: docker_server_plugin_profile_plugin_profiles

-- DROP TABLE docker_server_plugin_profile_plugin_profiles;

CREATE TABLE docker_server_plugin_profile_plugin_profiles
(
  docker_server_plugin_profile_id character varying(255) NOT NULL,
  plugin_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_3u412qsj2eg92xbabweuxjfyo FOREIGN KEY (docker_server_plugin_profile_id)
      REFERENCES docker_server_plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tn5xn8hgkc3572apy804jlav FOREIGN KEY (plugin_profiles_id)
      REFERENCES plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_tn5xn8hgkc3572apy804jlav UNIQUE (plugin_profiles_id)
)
WITH (
  OIDS=FALSE
);

