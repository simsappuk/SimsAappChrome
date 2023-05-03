-- author Mohammed Luqman Shareef
-- Create Tables for Docker Network
-- Table: docker_network, docker_network_entitled_users, docker_network_entitled_users_pks, docker_network_entitled_users_pks, docker_network_entitled_orgs
-- docker_network_entitled_user_groups, docker_network_entitled_groups_pks, docker_network_options, docker_network_aux_addresses,


CREATE TABLE docker_network
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
  network_id character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  internal boolean,
  enable_ipv6 boolean,
  scope character varying(255),
  driver character varying(255),
  driver_ character varying(255),
  subnet character varying(255),
  ip_range character varying(255),
  gateway character varying(255),
  status character varying(255),
  CONSTRAINT docker_network_pkey PRIMARY KEY (id),
  CONSTRAINT fk_docke_network_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_docker_network_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_entitled_users
(
  docker_network_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT docker_network_entitled_users_pkey PRIMARY KEY (docker_network_id, entitled_users_id),
  CONSTRAINT fk_docker_network_entitled_users_id FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT docker_network_entitled_users_users_id FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_entitled_users_pks
(
  docker_network_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT docker_network_entitled_users_pks_netid FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_entitled_user_groups
(
  docker_network_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT docker_network_entitled_user_groups_pkey PRIMARY KEY (docker_network_id, entitled_user_groups_id),
  CONSTRAINT fk_docker_network_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_docker_network_entitled_user_groups_netid FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_entitled_groups_pks
(
  docker_network_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_docker_network_entitled_groups_pks_netid FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_options
(
  docker_network_id character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  val character varying(512),
  CONSTRAINT docker_network_options_pkey PRIMARY KEY (docker_network_id, name),
  CONSTRAINT fk_docker_network FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_aux_addresses
(
  docker_network_id character varying(255) NOT NULL,
  host character varying(255) NOT NULL,
  address character varying(512),
  CONSTRAINT docker_network_aux_addresses_pkey PRIMARY KEY (docker_network_id, host),
  CONSTRAINT fk_docker_network FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE docker_network_entitled_orgs
(
  docker_network_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT docker_network_entitled_orgs_pkey PRIMARY KEY (docker_network_id, entitled_orgs_id),
  CONSTRAINT fk_docker_network_entitled_orgs FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_docker_network_entitled_orgs_netid FOREIGN KEY (docker_network_id)
      REFERENCES docker_network (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


