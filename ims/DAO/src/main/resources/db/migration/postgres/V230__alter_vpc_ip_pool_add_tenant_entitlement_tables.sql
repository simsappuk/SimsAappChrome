
-- Author: Faizan Mohammad

CREATE TABLE vpc_ip_pool_entitled_tenants
(
  vpc_ip_pool_id character varying(255) NOT NULL,
  entitled_tenants_id character varying(255) NOT NULL,
  CONSTRAINT vpc_ip_pool_entitled_tenants_pkey PRIMARY KEY (vpc_ip_pool_id, entitled_tenants_id),
  CONSTRAINT fk_vpc_ip_pool_entitled_tenants FOREIGN KEY (entitled_tenants_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vpc_ip_pool_entitled_tenants_netid FOREIGN KEY (vpc_ip_pool_id)
      REFERENCES vpc_ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE vpc_ip_pool_entitled_tenants_pks
(
  vpc_ip_pool_id character varying(255) NOT NULL,
  entitled_tenants_pks character varying(255),
  CONSTRAINT fk_vpc_ip_pool_entitled_tenants_pks_id FOREIGN KEY (vpc_ip_pool_id)
      REFERENCES vpc_ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
