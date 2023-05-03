-- Author: Mohammed Luqman Shareef
-- Table: network_acl and its child tables

DROP TABLE if exists network_acl_entitled_groups_pks;

DROP TABLE if exists network_acl_entitled_orgs;

DROP TABLE if exists network_acl_entitled_user_groups;

DROP TABLE if exists network_acl_entitled_users;

DROP TABLE if exists network_acl_entitled_users_pks;

DROP TABLE if exists network_acl;

CREATE TABLE network_acl
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
  description character varying(255),
  addresses character varying(255),
  state character varying(50),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT network_acl_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vpc_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vpc_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE network_acl_entitled_groups_pks
(
  network_acl_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_network_acl_entitled_groups_pks_id FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE network_acl_entitled_orgs
(
  network_acl_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT network_acl_entitled_orgs_pkey PRIMARY KEY (network_acl_id, entitled_orgs_id),
  CONSTRAINT fk_network_acl_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_network_acl_entitled_orgs_netid FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE network_acl_entitled_user_groups
(
  network_acl_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT network_acl_entitled_user_groups_pkey PRIMARY KEY (network_acl_id, entitled_user_groups_id),
  CONSTRAINT fk_network_acl_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_network_acl_entitled_user_groups_netid FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE network_acl_entitled_users
(
  network_acl_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT network_acl_entitled_users_pkey PRIMARY KEY (network_acl_id, entitled_users_id),
  CONSTRAINT network_acl_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_network_acl_entitled_users_id FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE network_acl_entitled_users_pks
(
  network_acl_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT network_acl_entitled_users_pks_id FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DROP TABLE IF EXISTS network_acl_rules;

CREATE TABLE network_acl_rules
(
  network_acl_id character varying(255) NOT NULL,
  rule_id character varying(255) NOT NULL,
  CONSTRAINT fk_network_acl_rules_ds FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_network_acl_rules_rule FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DROP TABLE IF EXISTS network_acl_rules_pks;

CREATE TABLE network_acl_rules_pks
(
  network_acl_id character varying(255) NOT NULL,
  rules_pks character varying(255),
  CONSTRAINT network_acl_rules_pks_id FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);