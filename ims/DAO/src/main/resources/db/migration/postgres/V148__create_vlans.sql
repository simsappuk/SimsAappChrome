-- author Faizan Mohammed
-- Create Tables for VLAN
-- Table: vlan

CREATE TABLE vlan
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
  CONSTRAINT vlan_pkey PRIMARY KEY (id),
  CONSTRAINT fk_vlan_owner FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
