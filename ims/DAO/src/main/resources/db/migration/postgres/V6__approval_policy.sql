-- Table: approval_policy

-- DROP TABLE approval_policy;

CREATE TABLE approval_policy
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
  tenant_pk character varying(255),
  resource_type character varying(50),
  environment character varying(50),
  task_type character varying(100),
  approval_required boolean,
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT approval_policy_pkey PRIMARY KEY (id),
  CONSTRAINT fk_approval_policy_users FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_approval_policy_tenant FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE approval_policy
  OWNER TO dchq;
