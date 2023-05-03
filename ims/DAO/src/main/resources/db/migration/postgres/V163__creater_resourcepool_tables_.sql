-- Author Mohammed Luqman Shareef
-- Create Tables for IP Pool and IP Service
-- Table: resource_pool

-- DROP TABLE resource_pool;

CREATE TABLE resource_pool
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
  cpu integer,
  mem integer,
  disk integer,
  provider_id character varying(255),
  cpu_used integer,
  mem_used integer,
  disk_used integer,
  CONSTRAINT resource_pool_pkey PRIMARY KEY (id),
  CONSTRAINT fk_resource_pool_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_resource_pool_user FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: resource_pool_entitled_groups_pks

-- DROP TABLE resource_pool_entitled_groups_pks;

CREATE TABLE resource_pool_entitled_groups_pks
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_85equ0ulmcsmf2lbdd811u906 FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: resource_pool_entitled_orgs

-- DROP TABLE resource_pool_entitled_orgs;

CREATE TABLE resource_pool_entitled_orgs
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_entitled_orgs_pkey PRIMARY KEY (resource_pool_id, entitled_orgs_id),
  CONSTRAINT fk_g5lxqxairqhl2qpwk8g2qk7bk FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_gx6ovu2xjmltgbos0qw61p2sg FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: resource_pool_entitled_user_groups

-- DROP TABLE resource_pool_entitled_user_groups;

CREATE TABLE resource_pool_entitled_user_groups
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_entitled_user_groups_pkey PRIMARY KEY (resource_pool_id, entitled_user_groups_id),
  CONSTRAINT fk_1gweq3xr4exxrk99lo7b0rksn FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_hitr4v6cyhpnxfhjp6x5gwg3h FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: resource_pool_entitled_users

-- DROP TABLE resource_pool_entitled_users;

CREATE TABLE resource_pool_entitled_users
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_entitled_users_pkey PRIMARY KEY (resource_pool_id, entitled_users_id),
  CONSTRAINT fk_ent_users_resource_pool FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ent_users_users FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: resource_pool_entitled_users_pks

-- DROP TABLE resource_pool_entitled_users_pks;

CREATE TABLE resource_pool_entitled_users_pks
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_resourse_pool_pks FOREIGN KEY (resource_pool_id)
      REFERENCES resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

