-- Table: plugin_history

-- DROP TABLE plugin_history;

CREATE TABLE plugin_history
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
  oldbase_script text,
  plugin_id character varying(255),
  owner_id character varying(255),
  CONSTRAINT plugin_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ods6rcmgjpxcovfeslg9qrlvc FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE plugin_history
  OWNER TO dchq;

