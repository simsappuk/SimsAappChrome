-- Author: Mohammed Shoukath Ali

-- Table: Pool and quota changes

--

ALTER TABLE resource_pool DROP CONSTRAINT IF EXISTS  fk_79lws28br7god7g1jumukf5w2;


ALTER TABLE resource_pool ADD COLUMN  rp_type  character varying(100);

ALTER TABLE resource_pool ADD COLUMN  az_id  character varying(100);

ALTER TABLE resource_pool ADD COLUMN az_name  character varying(100);

-- Table: resource_pool_entitled_tenants

-- DROP TABLE resource_pool_entitled_tenants;

CREATE TABLE resource_pool_entitled_tenants
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_tenants_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_entitled_tenants_pkey PRIMARY KEY (resource_pool_id, entitled_tenants_id),
  CONSTRAINT fk_4324pfqt7m2k5ta4t3dpos4fh FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_savnmbgyh6518o4sm49gyiacv FOREIGN KEY (entitled_tenants_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

  -- Table: resource_pool_entitled_tenants_pks

-- DROP TABLE resource_pool_entitled_tenants_pks;

CREATE TABLE resource_pool_entitled_tenants_pks
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_tenants_pks character varying(255),
  CONSTRAINT fk_myqobyy3yk6480utl9iesvul4 FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


