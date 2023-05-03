-- Author: Mohammed Luqman Shareef
-- Table: rule and its child tables

DROP TABLE if exists rule_entitled_groups_pks;

DROP TABLE if exists rule_entitled_orgs;

DROP TABLE if exists rule_entitled_user_groups;

DROP TABLE if exists rule_entitled_users;

DROP TABLE if exists rule_entitled_users_pks;

DROP TABLE if exists rule;

CREATE TABLE rule
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
  protocol character varying(50),
  port_range character varying(255),
  status character varying(50),
  bound character varying(50),
  action character varying(50),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT rule_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vpc_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_vpc_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE rule_entitled_groups_pks
(
  rule_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_rule_entitled_groups_pks_id FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE rule_entitled_orgs
(
  rule_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT rule_entitled_orgs_pkey PRIMARY KEY (rule_id, entitled_orgs_id),
  CONSTRAINT fk_rule_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rule_entitled_orgs_netid FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE rule_entitled_user_groups
(
  rule_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT rule_entitled_user_groups_pkey PRIMARY KEY (rule_id, entitled_user_groups_id),
  CONSTRAINT fk_rule_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rule_entitled_user_groups_netid FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE rule_entitled_users
(
  rule_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT rule_entitled_users_pkey PRIMARY KEY (rule_id, entitled_users_id),
  CONSTRAINT rule_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rule_entitled_users_id FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE rule_entitled_users_pks
(
  rule_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT rule_entitled_users_pks_id FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


DROP TABLE IF EXISTS subnet_rules;

CREATE TABLE subnet_rules
(
  subnet_id character varying(255) NOT NULL,
  rule_id character varying(255) NOT NULL,
  CONSTRAINT fk_subnet_rules_ds FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_subnet_rules_rule FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DROP TABLE IF EXISTS subnet_rules_pks;

CREATE TABLE subnet_rules_pks
(
  subnet_id character varying(255) NOT NULL,
  rules_pks character varying(255),
  CONSTRAINT subnet_rules_pks_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


DROP TABLE IF EXISTS security_group_rules;

CREATE TABLE security_group_rules
(
  security_group_id character varying(255) NOT NULL,
  rule_id character varying(255) NOT NULL,
  CONSTRAINT fk_sg_rules_ds FOREIGN KEY (security_group_id)
      REFERENCES security_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sg_rules_rule FOREIGN KEY (rule_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);