-- Author: Mohammed Luqman Shareef
-- Table: subnet and its child tables

DROP TABLE if exists subnet_entitled_groups_pks;

DROP TABLE if exists subnet_entitled_orgs;

DROP TABLE if exists subnet_entitled_user_groups;

DROP TABLE if exists subnet_entitled_users;

DROP TABLE if exists subnet_entitled_users_pks;

DROP TABLE if exists subnet;

CREATE TABLE subnet
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
  mask character varying(255),
  range character varying(255),
  state character varying(50),
  ipv4cidr character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT subnet_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vpc_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vpc_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE subnet_entitled_groups_pks
(
  subnet_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_subnet_entitled_groups_pks_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE subnet_entitled_orgs
(
  subnet_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT subnet_entitled_orgs_pkey PRIMARY KEY (subnet_id, entitled_orgs_id),
  CONSTRAINT fk_subnet_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_subnet_entitled_orgs_netid FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE subnet_entitled_user_groups
(
  subnet_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT subnet_entitled_user_groups_pkey PRIMARY KEY (subnet_id, entitled_user_groups_id),
  CONSTRAINT fk_subnet_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_subnet_entitled_user_groups_netid FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE subnet_entitled_users
(
  subnet_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT subnet_entitled_users_pkey PRIMARY KEY (subnet_id, entitled_users_id),
  CONSTRAINT subnet_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_subnet_entitled_users_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE subnet_entitled_users_pks
(
  subnet_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT subnet_entitled_users_pks_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


----



