-- Name: Product; Type: TABLE; Schema: public; Owner: dchq; Tablespace:

CREATE TABLE product (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    tenant_pk character varying(255),
    tenant_id character varying(255) NOT NULL,
    owner_pk character varying(255),
    uuid character varying(255),
    owner_id character varying(255),
    name  character varying(255),
    description text,
    public_page_enabled BOOLEAN DEFAULT false,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT uk_8e43klacv5of1d2xo6h95ag3b UNIQUE (name)
);


-- Table: product_entitled_blueprint

-- DROP TABLE product_entitled_blueprint;

CREATE TABLE product_entitled_blueprint
(
  product_id character varying(255) NOT NULL,
  entitled_blueprint_id character varying(255) NOT NULL,
  CONSTRAINT product_entitled_blueprint_prkey PRIMARY KEY (product_id, entitled_blueprint_id),
  CONSTRAINT fk_bp_product_id FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_product_entitled_blueprint FOREIGN KEY (entitled_blueprint_id)
      REFERENCES blueprint (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product_entitled_blueprint
  OWNER TO dchq;
