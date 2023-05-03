--drop table public.resource_pool_daily_entitled_groups_pks;
CREATE TABLE public.resource_pool_daily_entitled_groups_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_85equ0ulmcsmf2lbdd811u906 FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_groups_pks
  OWNER TO dchq;

-- Table: public.resource_pool_daily_entitled_tenants

-- DROP TABLE public.resource_pool_daily_entitled_tenants;

CREATE TABLE public.resource_pool_daily_entitled_tenants
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_tenants_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_tenants_pkey PRIMARY KEY (resource_pool_daily_id, entitled_tenants_id),
  CONSTRAINT fk_4324pfqt7m2k5ta4t3dpos4fh FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
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

-- DROP TABLE public.resource_pool_daily_entitled_tenants_pks

CREATE TABLE public.resource_pool_daily_entitled_tenants_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_tenants_pks character varying(255),
  CONSTRAINT fk_myqobyy3yk6480utl9iesvul4 FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: public.resource_pool_daily_entitled_user_groups

-- DROP TABLE public.resource_pool_daily_entitled_user_groups

CREATE TABLE public.resource_pool_daily_entitled_user_groups
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_user_groups_pkey PRIMARY KEY (resource_pool_daily_id, entitled_user_groups_id),
  CONSTRAINT fk_1gweq3xr4exxrk99lo7b0rksn FOREIGN KEY (entitled_user_groups_id)
      REFERENCES public.user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_hitr4v6cyhpnxfhjp6x5gwg3h FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: public.resource_pool_daily_entitled_users

-- DROP TABLE public.resource_pool_daily_entitled_users;

CREATE TABLE public.resource_pool_daily_entitled_users
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_users_pkey PRIMARY KEY (resource_pool_daily_id, entitled_users_id),
  CONSTRAINT fk_ent_users_resource_pool FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
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


-- Table: public.resource_pool_daily_entitled_users_pks

-- DROP TABLE public.resource_pool_daily_entitled_users_pks;

CREATE TABLE public.resource_pool_daily_entitled_users_pks
(
  resource_pool_daily_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_resourse_pool_pks FOREIGN KEY (resource_pool_daily_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_users_pks
  OWNER TO dchq;