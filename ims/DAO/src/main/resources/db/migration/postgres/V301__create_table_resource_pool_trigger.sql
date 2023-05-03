CREATE TABLE resource_pool_trigger
(
  id character varying(255) NOT NULL,
  rp_id character varying(255) NOT NULL,
  trigger_resource character varying(255) NOT NULL,
  trigger_percentage integer,
  trigger_alert_type character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  CONSTRAINT resource_pool_trigger_pkey PRIMARY KEY (id),
  CONSTRAINT fk_resource_pool_id_trigger FOREIGN KEY (rp_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);