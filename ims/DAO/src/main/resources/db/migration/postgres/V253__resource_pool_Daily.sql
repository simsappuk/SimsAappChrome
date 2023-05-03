
CREATE TABLE public.resource_pool_audit
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
  entitlement character varying(255),
  entitlement_type character varying(255),
  name character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  cpu integer,
  mem integer,
  disk integer,
  provider_id character varying(255),
  cpu_used integer,
  mem_used integer,
  disk_used integer,
  rp_type character varying(100),
  az_id character varying(100),
  az_name character varying(100),
  cpu_reserved integer,
  mem_reserved integer,
  disk_reserved integer,
  audit_date date DEFAULT ('now'::text)::date,
  audit_date_start timestamp without time zone NOT NULL DEFAULT now(),
  audit_date_end timestamp without time zone,
  CONSTRAINT resource_pool_audit_pkey PRIMARY KEY (id, audit_date_start),
  CONSTRAINT fk_resource_pool_audit_user FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_resource_pool_tenant FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_audit
  OWNER TO dchq;
-- Table: public.resource_pool_daily

-- DROP TABLE public.resource_pool_daily;

CREATE TABLE public.resource_pool_daily
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
  entitlement character varying(255),
  entitlement_type character varying(255),
  name character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  cpu integer,
  mem integer,
  disk integer,
  provider_id character varying(255),
  cpu_used integer,
  mem_used integer,
  disk_used integer,
  rp_type character varying(100),
  az_id character varying(100),
  az_name character varying(100),
  cpu_reserved integer,
  mem_reserved integer,
  disk_reserved integer,
  day_id timestamp without time zone NOT NULL,
  CONSTRAINT fk_resource_pool_daily_tenant FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_resource_pool_daily_user FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily
  OWNER TO dchq;
