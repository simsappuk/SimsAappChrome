
-- author Mohammed Shoukath Ali
-- alter TABLE blueprint and plugin;
-- add column grid_id


ALTER TABLE blueprint ADD COLUMN grid_blueprint_id character varying(255);
ALTER TABLE plugin ADD COLUMN grid_plugin_id character varying(255);

--
-- TOC entry 284 (class 1259 OID 141462)
-- Name: blueprint_run; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

-- Table: blueprint_run

-- DROP TABLE blueprint_run;

CREATE TABLE blueprint_run
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
  dt timestamp without time zone,
  owner_id character varying(255),
  CONSTRAINT blueprint_run_pkey PRIMARY KEY (id),
  CONSTRAINT fk_tfdtnsdr12wt31khjgfixfilm FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


ALTER TABLE star OWNER TO dchq;
