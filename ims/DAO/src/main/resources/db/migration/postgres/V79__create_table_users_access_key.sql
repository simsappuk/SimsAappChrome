-- author Mohammed Luqman Shareef

-- drop table users_access_key;

-- Table: users_access_key

-- DROP TABLE users_access_key;

CREATE TABLE users_access_key
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  password character varying(255),
  user_id character varying(255),
  access_key character varying(255) unique,
  owner_id character varying(255),
  owner_pk character varying(255),
  uuid character varying(255),
  CONSTRAINT users_access_key_pkey PRIMARY KEY (id),
  CONSTRAINT fk_1dg427qt898j7shqnvv5s617k FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users_access_key
  OWNER TO dchq;

GRANT ALL ON TABLE users_access_key TO dchq;