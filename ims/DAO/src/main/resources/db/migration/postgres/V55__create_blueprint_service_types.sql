-- Table: blueprint_service_types

-- DROP TABLE blueprint_service_types;

CREATE TABLE blueprint_service_types
(
  blueprint_id character varying(255) NOT NULL,
  service_types character varying(255),
  CONSTRAINT fk_k10gduldjcmy8grp8d5olx560 FOREIGN KEY (blueprint_id)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE blueprint_service_types
  OWNER TO dchq;

  alter table blueprint drop column if exists  service_type;