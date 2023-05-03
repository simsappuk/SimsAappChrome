-- create table

CREATE TABLE comment
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
  blueprint_id character varying(255),
  commenter_id character varying(255),
  commenter_username character varying(255),
  dt timestamp without time zone,
  txt text,
  owner_id character varying(255),
  CONSTRAINT comment_pkey PRIMARY KEY (id),
  CONSTRAINT fk_64b1wbrgy9mv2p2ifv2s4fc62 FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)