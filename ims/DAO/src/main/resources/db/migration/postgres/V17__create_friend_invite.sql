-- create friend_invite table

CREATE TABLE friend_invite
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
  friend_email character varying(255),
  msg text,
  subject text,
  owner_id character varying(255),
  CONSTRAINT friend_invite_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ce76tohxt43qbo347dxsynj6e FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)