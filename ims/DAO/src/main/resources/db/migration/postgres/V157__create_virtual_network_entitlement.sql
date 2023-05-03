-- author Faizan Mohammed
-- Create Tables for virtual_network
-- Table: virtual_network_entitled_users, virtual_network_entitled_users_pks, virtual_network_entitled_user_groups, virtual_network_entitled_groups_pks, virtual_network_entitled_orgs 

ALTER TABLE virtual_network ADD COLUMN tenant_pk character varying(255);

ALTER TABLE virtual_network ADD COLUMN entitlement character varying(255);

ALTER TABLE virtual_network ADD COLUMN entitlement_type character varying(255);

ALTER TABLE virtual_network ADD COLUMN tenant_id character varying(255);

UPDATE virtual_network v set tenant_id = (SELECT u.tenant_id from users u WHERE u.id=v.owner_id);

ALTER TABLE virtual_network ALTER COLUMN  tenant_id SET NOT NULL;

ALTER TABLE virtual_network ADD CONSTRAINT fk_virtual_network_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE virtual_network_entitled_users
(
  virtual_network_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT virtual_network_entitled_users_pkey PRIMARY KEY (virtual_network_id, entitled_users_id),
  CONSTRAINT fk_virtual_network_entitled_users_id FOREIGN KEY (virtual_network_id)
      REFERENCES virtual_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT virtual_network_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE virtual_network_entitled_users_pks
(
  virtual_network_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT virtual_network_entitled_users_pks_netid FOREIGN KEY (virtual_network_id)
      REFERENCES virtual_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE virtual_network_entitled_user_groups
(
  virtual_network_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT virtual_network_entitled_user_groups_pkey PRIMARY KEY (virtual_network_id, entitled_user_groups_id),
  CONSTRAINT fk_virtual_network_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_network_entitled_user_groups_netid FOREIGN KEY (virtual_network_id)
      REFERENCES virtual_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE virtual_network_entitled_groups_pks
(
  virtual_network_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_virtual_network_entitled_groups_pks_netid FOREIGN KEY (virtual_network_id)
      REFERENCES virtual_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE virtual_network_entitled_orgs
(
  virtual_network_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT virtual_network_entitled_orgs_pkey PRIMARY KEY (virtual_network_id, entitled_orgs_id),
  CONSTRAINT fk_virtual_network_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_network_entitled_orgs_netid FOREIGN KEY (virtual_network_id)
      REFERENCES virtual_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);