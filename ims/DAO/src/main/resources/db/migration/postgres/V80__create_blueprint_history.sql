-- Table: blueprint_history

-- DROP TABLE blueprint_history;

CREATE TABLE blueprint_history
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
  old_yml text,
  owner_id character varying(255),
  CONSTRAINT blueprint_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_kphs5kv86es0fdamajt2trfn0 FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE blueprint_history
  OWNER TO dchq;
