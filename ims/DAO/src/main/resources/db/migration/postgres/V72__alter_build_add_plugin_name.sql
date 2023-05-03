-- Table: build
-- author Mohammed Shoukath Ali

ALTER TABLE build ADD COLUMN name character varying(255);


-- Table: build_pre_build_plugins

-- DROP TABLE build_pre_build_plugins;

CREATE TABLE build_pre_build_plugins
(
  build_id character varying(255) NOT NULL,
  pre_build_plugins_id character varying(255) NOT NULL,
  CONSTRAINT build_pre_build_plugins_pkey PRIMARY KEY (build_id, pre_build_plugins_id),
  CONSTRAINT fk_m1r19tcgpqa58i592f8et1vnl FOREIGN KEY (build_id)
      REFERENCES build (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sy9i9r02t2riaoqvb7vu4kg84 FOREIGN KEY (pre_build_plugins_id)
      REFERENCES plugin (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE build_pre_build_plugins
  OWNER TO dchq;

  
  -- Table: build_post_build_plugins

-- DROP TABLE build_post_build_plugins;

CREATE TABLE build_post_build_plugins
(
  build_id character varying(255) NOT NULL,
  post_build_plugins_id character varying(255) NOT NULL,
  CONSTRAINT build_post_build_plugins_pkey PRIMARY KEY (build_id, post_build_plugins_id),
  CONSTRAINT fk_4tl881tghhbudf2io7i0v6ip6 FOREIGN KEY (post_build_plugins_id)
      REFERENCES plugin (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ai6agqmmt3fcy86tnbmf967ar FOREIGN KEY (build_id)
      REFERENCES build (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE build_post_build_plugins
  OWNER TO dchq;



