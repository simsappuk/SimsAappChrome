-- Author Mohammed Luqman Shareef
-- Create Tables for IP Pool and IP Service

-- Table: ip_pool

-- DROP TABLE ip_pool;

CREATE TABLE ip_pool
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
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  dns character varying(255),
  gateway character varying(255),
  subnet character varying(255),
  virtual_switch character varying(255),
  vlan character varying(255),
  CONSTRAINT ip_pool_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ip_pool_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ip_pool_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: ip_pool_entitled_groups_pks

-- DROP TABLE ip_pool_entitled_groups_pks;

CREATE TABLE ip_pool_entitled_groups_pks
(
  ip_pool_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_ip_pool_entitled_groups_pks_netid FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: ip_pool_entitled_orgs

-- DROP TABLE ip_pool_entitled_orgs;

CREATE TABLE ip_pool_entitled_orgs
(
  ip_pool_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT ip_pool_entitled_orgs_pkey PRIMARY KEY (ip_pool_id, entitled_orgs_id),
  CONSTRAINT fk_ip_pool_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ip_pool_entitled_orgs_netid FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: ip_pool_entitled_user_groups

-- DROP TABLE ip_pool_entitled_user_groups;

CREATE TABLE ip_pool_entitled_user_groups
(
  ip_pool_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT ip_pool_entitled_user_groups_pkey PRIMARY KEY (ip_pool_id, entitled_user_groups_id),
  CONSTRAINT fk_ip_pool_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ip_pool_entitled_user_groups_netid FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: ip_pool_entitled_users

-- DROP TABLE ip_pool_entitled_users;

CREATE TABLE ip_pool_entitled_users
(
  ip_pool_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT ip_pool_entitled_users_pkey PRIMARY KEY (ip_pool_id, entitled_users_id),
  CONSTRAINT fk_ip_pool_entitled_users_id FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ip_pool_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: ip_pool_entitled_users_pks

-- DROP TABLE ip_pool_entitled_users_pks;

CREATE TABLE ip_pool_entitled_users_pks
(
  ip_pool_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT ip_pool_entitled_users_pks_netid FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: ip

-- DROP TABLE ip;

CREATE TABLE ip
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  uuid character varying(255),
  ip_pool_id character varying(255),
  private_ip character varying(255),
  public_ip character varying(255),
  used_by_id character varying(255),
  ip_status character varying(50),
  owner_id character varying(255),
  owner_pk character varying(255),
  name character varying(255),
  CONSTRAINT ip_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ip_pool FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: ip_pool_ips

-- DROP TABLE ip_pool_ips;

CREATE TABLE ip_pool_ips
(
  ip_pool_id character varying(255) NOT NULL,
  ips_id character varying(255) NOT NULL,
  CONSTRAINT fk_ip_pool FOREIGN KEY (ip_pool_id)
      REFERENCES ip_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

