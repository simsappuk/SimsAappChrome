-- Table: docker_server_plugin_profiles

-- DROP TABLE docker_server_plugin_profiles;

CREATE TABLE docker_server_plugin_profiles
(
  docker_server_id character varying(255) NOT NULL,
  plugin_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_f8rhstt3dujxnj9eoy80gp2jy FOREIGN KEY (docker_server_id)
      REFERENCES docker_server (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_q085wyguwnwf786tgwxmq8h45 FOREIGN KEY (plugin_profiles_id)
      REFERENCES plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_server_plugin_profiles
  OWNER TO dchq;