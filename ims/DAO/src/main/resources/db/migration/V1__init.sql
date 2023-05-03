-- Table: public.users

-- DROP TABLE public.users;


CREATE TABLE public.tenant
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
  name character varying(255),
  owner_id character varying(255),
  title character varying(255),
  logo character varying(512),
  color character varying(255),
  company character varying(255),
  department character varying(255),
  contact_name character varying(255),
  contact_title character varying(255),
  contact_email character varying(255),
  contact_phone character varying(255),
  vm_prefix character varying(255),
  CONSTRAINT tenant_pkey PRIMARY KEY (id),
  CONSTRAINT uk_dcxf3ksi0gyn1tieeq0id96lm UNIQUE (name)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.users
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
  company character varying(255),
  cpu_used integer,
  email character varying(255),
  enabled boolean,
  firstname character varying(255),
  job_title character varying(255),
  lastname character varying(255),
  memory_used integer,
  password character varying(255),
  phone_number character varying(255),
  username character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  organization_id character varying(255),
  preferred_data_center_id character varying(255),
  profile_id character varying(255),
  cli_black_list text,
  promo_code character varying(255),
  org_id character varying(255),
  org_description text,
  org_public_page_enabled boolean DEFAULT false,
  source character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id),
CONSTRAINT fk_hprlrsyr7nwby3t8o76585rwb FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_i564ddj641vg5s0u9m9drs5ei FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.user_group
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
  name character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT user_group_pkey PRIMARY KEY (id),
  CONSTRAINT fk_9ubn4j9v4kpds9teitt19lsck FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_k4031w2lg2cpc6jlnb0ocvjje FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE public.users_user_groups
(
  users_id character varying(255) NOT NULL,
  user_groups_id character varying(255) NOT NULL,
  CONSTRAINT users_user_groups_pkey PRIMARY KEY (users_id, user_groups_id),
  CONSTRAINT fk_8cv2deco6evdweubh3gw9v1id FOREIGN KEY (users_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lqlmincmr8rucygmp3mnqcw1d FOREIGN KEY (user_groups_id)
      REFERENCES public.user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


