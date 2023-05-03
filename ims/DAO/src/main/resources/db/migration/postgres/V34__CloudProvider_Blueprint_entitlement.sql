alter table registry_account add column  blueprint_entitlement_type character varying(255);

update registry_account set blueprint_entitlement_type = 'ALL_BLUEPRINTS' where blueprint_entitlement_type is null  and account_type <> 'DOCKER_REGISTRY';

-- Table: registry_account_entitled_blueprint_pks

--DROP TABLE registry_account_entitled_blueprint_pks;

CREATE TABLE registry_account_entitled_blueprint_pks
(
  registry_account_id character varying(255) NOT NULL,
  entitled_blueprint_pks character varying(255) NOT NULL,
  CONSTRAINT registry_account_entitled_blueprint_pkey PRIMARY KEY (registry_account_id, entitled_blueprint_pks),
  CONSTRAINT fk_kj59obc29i88pd13kyf9fn6tn FOREIGN KEY (registry_account_id)
      REFERENCES registry_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_l3c823p6k4bsluv5hfr7fgwjd FOREIGN KEY (entitled_blueprint_pks)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE registry_account_entitled_blueprint_pks
  OWNER TO dchq;




-- Table: registry_account_entitled_blueprint

-- DROP TABLE registry_account_entitled_blueprint;

CREATE TABLE registry_account_entitled_blueprint
(
  registry_account_id character varying(255) NOT NULL,
  entitled_blueprint_id character varying(255) NOT NULL,
  CONSTRAINT registry_account_entitled_blueprint_prkey PRIMARY KEY (registry_account_id, entitled_blueprint_id),
  CONSTRAINT fk_bp_registry_account_id FOREIGN KEY (registry_account_id)
      REFERENCES registry_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_registry_account_entitled_blueprint FOREIGN KEY (entitled_blueprint_id)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE registry_account_entitled_blueprint
  OWNER TO dchq;
