-- Table: virtual_app_docker_volume_tasks

-- DROP TABLE virtual_app_docker_volume_tasks;

CREATE TABLE virtual_app_docker_volume_tasks
(
  virtual_app_id character varying(255) NOT NULL,
  docker_volume_tasks_id character varying(255) NOT NULL,
  CONSTRAINT fk_n2p20238cr5gmb5guuq9al0lg FOREIGN KEY (virtual_app_id)
      REFERENCES virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_9e6laecx68danu4jimymx159x UNIQUE (docker_volume_tasks_id)
)
WITH (
  OIDS=FALSE
);

-- Table: virtual_app_docker_volumes

-- DROP TABLE virtual_app_docker_volumes;

CREATE TABLE virtual_app_docker_volumes
(
  virtual_app_id character varying(255) NOT NULL,
  docker_volumes_id character varying(255) NOT NULL,
  CONSTRAINT fk_jxpob7cyckv6y1yru7bn178dd FOREIGN KEY (virtual_app_id)
      REFERENCES virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pj2xd3ufe9wga4uupvyupdlf5 FOREIGN KEY (docker_volumes_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_pj2xd3ufe9wga4uupvyupdlf5 UNIQUE (docker_volumes_id)
)
WITH (
  OIDS=FALSE
);

-- Table: docker_volume

-- UPDATE Column virtual_app_id

ALTER TABLE docker_volume ADD COLUMN virtual_app_id character varying(255);










