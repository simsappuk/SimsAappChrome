-- Table: blueprint_cloud_params

create table blueprint_cloud_params(
	blueprint_id character varying(255),
	cloud_params character varying(1024),
	cloud_params_key character varying(255)
);

ALTER TABLE blueprint_cloud_params
  OWNER TO dchq;


ALTER TABLE ONLY blueprint_cloud_params
    ADD CONSTRAINT fk_3k0hqxxx7laljhp20g6wkbx07 FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);

