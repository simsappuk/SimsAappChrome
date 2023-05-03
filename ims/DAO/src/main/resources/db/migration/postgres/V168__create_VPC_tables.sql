-- Author: Mohammed Luqman Shareef
-- Table: virtual_private_cloud and its child tables

DROP TABLE if exists virtual_private_cloud_entitled_groups_pks;

DROP TABLE if exists virtual_private_cloud_entitled_orgs;

DROP TABLE if exists virtual_private_cloud_entitled_user_groups;

DROP TABLE if exists virtual_private_cloud_entitled_users;

DROP TABLE if exists virtual_private_cloud_entitled_users_pks;

DROP TABLE if exists virtual_private_cloud;

CREATE TABLE virtual_private_cloud
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  owner_pk character varying(255),
  uuid character varying(255),
  tenant_pk character varying(255),
  entitlement character varying(255),
  entitlement_type character varying(255),
  name character varying(255),
  url character varying(255),
  username character varying(255),
  password character varying(255),
  state character varying(50),
  ipv4_cidr character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT virtual_private_cloud_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vpc_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vpc_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE virtual_private_cloud_entitled_groups_pks
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_virtual_private_cloud_entitled_groups_pks_id FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE virtual_private_cloud_entitled_orgs
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT virtual_private_cloud_entitled_orgs_pkey PRIMARY KEY (virtual_private_cloud_id, entitled_orgs_id),
  CONSTRAINT fk_virtual_private_cloud_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_private_cloud_entitled_orgs_netid FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE virtual_private_cloud_entitled_user_groups
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT virtual_private_cloud_entitled_user_groups_pkey PRIMARY KEY (virtual_private_cloud_id, entitled_user_groups_id),
  CONSTRAINT fk_virtual_private_cloud_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_private_cloud_entitled_user_groups_netid FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE virtual_private_cloud_entitled_users
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT virtual_private_cloud_entitled_users_pkey PRIMARY KEY (virtual_private_cloud_id, entitled_users_id),
  CONSTRAINT virtual_private_cloud_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_virtual_private_cloud_entitled_users_id FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE virtual_private_cloud_entitled_users_pks
(
  virtual_private_cloud_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT virtual_private_cloud_entitled_users_pks_id FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);






