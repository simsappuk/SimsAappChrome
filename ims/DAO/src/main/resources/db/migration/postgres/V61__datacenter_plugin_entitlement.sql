alter table data_center add  column plugin_entitlement_type character varying(255);

update data_center set plugin_entitlement_type  = 'ALL_PLUGINS' where plugin_entitlement_type is null;


-- Table: data_center_entitled_plugin

-- DROP TABLE data_center_entitled_plugin;

CREATE TABLE data_center_entitled_plugin
(
  data_center_id character varying(255) NOT NULL,
  entitled_plugin_id character varying(255) NOT NULL,
  CONSTRAINT data_center_entitled_plugin_prkey PRIMARY KEY (data_center_id, entitled_plugin_id),
  CONSTRAINT fk_pg_data_center_id FOREIGN KEY (data_center_id)
      REFERENCES data_center (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_data_center_entitled_plugin FOREIGN KEY (entitled_plugin_id)
      REFERENCES plugin (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE data_center_entitled_plugin
  OWNER TO dchq;

  
  
  -- Table: data_center_entitled_plugin_pks

-- DROP TABLE data_center_entitled_plugin_pks;

CREATE TABLE data_center_entitled_plugin_pks
(
  data_center_id character varying(255) NOT NULL,
  entitled_plugin_pks character varying(255) NOT NULL,
  CONSTRAINT data_center_entitled_plugin_pkey PRIMARY KEY (data_center_id, entitled_plugin_pks),
  CONSTRAINT fk_pg_dc_id FOREIGN KEY (data_center_id)
      REFERENCES data_center (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pg_pks FOREIGN KEY (entitled_plugin_pks)
      REFERENCES plugin (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE data_center_entitled_plugin_pks
  OWNER TO dchq;
