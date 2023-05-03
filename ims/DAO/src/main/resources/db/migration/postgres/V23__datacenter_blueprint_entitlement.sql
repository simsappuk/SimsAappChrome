-- Table: data_center_entitled_blueprint_pkst

--DROP TABLE data_center_entitled_blueprint_pks;

CREATE TABLE data_center_entitled_blueprint_pks
(
  data_center_id character varying(255) NOT NULL,
  entitled_blueprint_pks character varying(255) NOT NULL,
  CONSTRAINT data_center_entitled_blueprint_pkey PRIMARY KEY (data_center_id, entitled_blueprint_pks),
  CONSTRAINT fk_kj59obc29i88pd13kyf9fn6tn FOREIGN KEY (data_center_id)
      REFERENCES data_center (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_l3c823p6k4bsluv5hfr7fgwjd FOREIGN KEY (entitled_blueprint_pks)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE data_center_entitled_blueprint_pks
  OWNER TO dchq;


alter table data_center add column  blueprint_entitlement_type character varying(255);


-- Table: data_center_entitled_blueprint

-- DROP TABLE data_center_entitled_blueprint;

CREATE TABLE data_center_entitled_blueprint
(
  data_center_id character varying(255) NOT NULL,
  entitled_blueprint_id character varying(255) NOT NULL,
  CONSTRAINT data_center_entitled_blueprint_prkey PRIMARY KEY (data_center_id, entitled_blueprint_id),
  CONSTRAINT fk_bp_data_center_id FOREIGN KEY (data_center_id)
      REFERENCES data_center (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_data_center_entitled_blueprint FOREIGN KEY (entitled_blueprint_id)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE data_center_entitled_blueprint
  OWNER TO dchq;
