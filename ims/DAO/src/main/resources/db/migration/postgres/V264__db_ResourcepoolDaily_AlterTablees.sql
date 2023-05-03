DROP  TABLE public.resource_pool_daily CASCADE;

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
  resource_pool_id character varying(255),
  resource_pool_daily_id character varying(255),
  description character varying(255),
  CONSTRAINT resource_pool_daily_pkey PRIMARY KEY (id),
  CONSTRAINT fk_resource_pool_daily_tenant FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_resource_pool_user FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily
  OWNER TO dchq;


DROP TABLE public.resource_pool_daily_entitled_users_pks CASCADE;

CREATE TABLE public.resource_pool_daily_entitled_users_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_resourse_pool_pks FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: public.resource_pool_entitled_users

 DROP TABLE public.resource_pool_daily_entitled_users cascade;

CREATE TABLE public.resource_pool_daily_entitled_users
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_users_pkey PRIMARY KEY (resource_pool_daily_id, entitled_users_id),
  CONSTRAINT fk_ent_users_resource_pool_daily FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ent_users_users FOREIGN KEY (entitled_users_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_users
  OWNER TO dchq;

-- Table: public.resource_pool_daily_entitled_groups_pks

 DROP TABLE public.resource_pool_daily_entitled_groups_pks cascade;

CREATE TABLE public.resource_pool_daily_entitled_groups_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_85equ0ulmcsmf2lbdd811u906 FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_groups_pks
  OWNER TO dchq;

  -- Table: public.resource_pool_daily_entitled_orgs

 DROP TABLE public.resource_pool_daily_entitled_orgs cascade;

CREATE TABLE public.resource_pool_daily_entitled_orgs
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_orgs_pkey PRIMARY KEY (resource_pool_daily_id, entitled_orgs_id),
  CONSTRAINT fk_g5lxqxairqhl2qpwk8g2qk7bk FOREIGN KEY (entitled_orgs_id)
      REFERENCES public.organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_gx6ovu2xjmltgbos0qw61p2sg FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_orgs
  OWNER TO dchq;

-- Table: public.resource_pool_daily_entitled_tenants

 DROP TABLE public.resource_pool_daily_entitled_tenants cascade;

CREATE TABLE public.resource_pool_daily_entitled_tenants
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_tenants_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_tenants_pkey PRIMARY KEY (resource_pool_daily_id, entitled_tenants_id),
  CONSTRAINT fk_4324pfqt7m2k5ta4t3dpos4fh FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_savnmbgyh6518o4sm49gyiacv FOREIGN KEY (entitled_tenants_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_tenants
  OWNER TO dchq;

  -- Table: public.resource_pool_daily_entitled_tenants_pks

 DROP TABLE public.resource_pool_daily_entitled_tenants_pks;

CREATE TABLE public.resource_pool_daily_entitled_tenants_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_tenants_pks character varying(255),
  CONSTRAINT fk_myqobyy3yk6480utl9iesvul4 FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_tenants_pks
  OWNER TO dchq;

  -- Table: public.resource_pool_daily_entitled_user_groups

DROP TABLE public.resource_pool_daily_entitled_user_groups cascade;

CREATE TABLE public.resource_pool_daily_entitled_user_groups
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_user_groups_pkey PRIMARY KEY (resource_pool_daily_id, entitled_user_groups_id),
  CONSTRAINT fk_1gweq3xr4exxrk99lo7b0rksn FOREIGN KEY (entitled_user_groups_id)
      REFERENCES public.user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_hitr4v6cyhpnxfhjp6x5gwg3h FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_user_groups
  OWNER TO dchq;

-- Table: public.resource_pool_entitled_users

 DROP TABLE public.resource_pool_daily_entitled_users cascade;

CREATE TABLE public.resource_pool_daily_entitled_users
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_entitled_users_pkey_daily PRIMARY KEY (resource_pool_daily_id, entitled_users_id),
  CONSTRAINT fk_ent_users_resource_pool_daily FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ent_users_users FOREIGN KEY (entitled_users_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_entitled_users
  OWNER TO dchq;

-- Table: public.resource_pool_entitled_users_pks

DROP TABLE public.resource_pool_daily_entitled_users_pks cascade;

CREATE TABLE public.resource_pool_daily_entitled_users_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_resourse_pool_pks_daily FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool_daily (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_users_pks
  OWNER TO dchq;




