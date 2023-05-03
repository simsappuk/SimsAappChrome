-- author Faizan Mohammed
-- Create Tables for virtual_network_pool, virtual_network, virtual_network_task
-- Table: virtual_network

ALTER TABLE public.vlan DROP CONSTRAINT fk_vlan_owner;

drop table if exists vlan;

CREATE TABLE virtual_network_pool
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
  vlan_id character varying(255),
  status character varying(255),
  driver character varying(255),
  CONSTRAINT virtual_networks_pkey PRIMARY KEY (id)
);


CREATE TABLE virtual_network
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
  owner_pk character varying(255),
  name character varying(255),
  notes character varying(255),
  vlan_id character varying(255),
  owner_id character varying(255),
  status character varying(255),
  driver character varying(255),
  used_by character varying(255),
  CONSTRAINT virtual_network_pkey PRIMARY KEY (id),
  CONSTRAINT fk_virtual_network_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE virtual_network_task (
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
    virtual_network_task_status character varying(255),
    virtual_network_task_type character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    waiting_on character varying(255),
    owner_id character varying(255),
    virtual_network_id character varying(255)
);