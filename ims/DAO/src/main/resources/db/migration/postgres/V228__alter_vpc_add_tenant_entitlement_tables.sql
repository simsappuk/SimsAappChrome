
-- Author: Faizan Mohammad

CREATE TABLE virtual_private_cloud_entitled_tenants
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_tenants_id character varying(255) NOT NULL,
  CONSTRAINT virtual_private_cloud_entitled_tenants_pkey PRIMARY KEY (virtual_private_cloud_id, entitled_tenants_id),
  CONSTRAINT fk_virtual_private_cloud_entitled_tenants FOREIGN KEY (entitled_tenants_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_private_cloud_entitled_tenants_netid FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE virtual_private_cloud_entitled_tenants_pks
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_tenants_pks character varying(255),
  CONSTRAINT fk_virtual_private_cloud_entitled_tenants_pks_id FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
