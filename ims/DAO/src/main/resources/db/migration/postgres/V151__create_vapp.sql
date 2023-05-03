-- Table: VApp
-- author Mohammed Shoukath Ali


-- Table: virtual_app

-- DROP TABLE virtual_app;

CREATE TABLE virtual_app
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
  entitlement_type character varying(255),
  name character varying(255),
  virtual_app_status character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT virtual_app_pkey PRIMARY KEY (id),
  CONSTRAINT fk_4l9oswep0dpcpmgoo5wncwnj0 FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_r2d5xj54qp3cqb69rv8jav5ku FOREIGN KEY (owner_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: virtual_app_docker_server_tasks

-- DROP TABLE virtual_app_docker_server_tasks;

CREATE TABLE virtual_app_docker_server_tasks
(
  virtual_app_id character varying(255) NOT NULL,
  docker_server_tasks_id character varying(255) NOT NULL,
  CONSTRAINT fk_brngauowspxa0o9j09exluo9v FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ea6xqwt5ra17xsechrfbymfv5 FOREIGN KEY (docker_server_tasks_id)
      REFERENCES public.docker_server_task (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_ea6xqwt5ra17xsechrfbymfv5 UNIQUE (docker_server_tasks_id)
)
WITH (
  OIDS=FALSE
);

-- Table:virtual_app_docker_servers

-- DROP TABLE virtual_app_docker_servers;

CREATE TABLE virtual_app_docker_servers
(
  virtual_app_id character varying(255) NOT NULL,
  docker_servers_id character varying(255) NOT NULL,
  CONSTRAINT fk_8fkhxgg5aas0w1eqrym38tvv FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ote8r1b1p14s6kriv5y1bbn1w FOREIGN KEY (docker_servers_id)
      REFERENCES public.docker_server (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_ote8r1b1p14s6kriv5y1bbn1w UNIQUE (docker_servers_id)
)
WITH (
  OIDS=FALSE
);


-- Table: virtual_app_entitled_groups_pks

-- DROP TABLE virtual_app_entitled_groups_pks;

CREATE TABLE virtual_app_entitled_groups_pks
(
  virtual_app_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_1255oex2uralppnqkjqwl5x50 FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table:virtual_app_entitled_orgs

-- DROP TABLE virtual_app_entitled_orgs;

CREATE TABLE virtual_app_entitled_orgs
(
  virtual_app_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT virtual_app_entitled_orgs_pkey PRIMARY KEY (virtual_app_id, entitled_orgs_id),
  CONSTRAINT fk_6fufmiawyam2lwuai53epjq6o FOREIGN KEY (entitled_orgs_id)
      REFERENCES public.organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_jfc8eiao2xb4k48jf85x58myw FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: virtual_app_entitled_user_groups

-- DROP TABLE virtual_app_entitled_user_groups;

CREATE TABLE virtual_app_entitled_user_groups
(
  virtual_app_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT virtual_app_entitled_user_groups_pkey PRIMARY KEY (virtual_app_id, entitled_user_groups_id),
  CONSTRAINT fk_gjn6bq7n6fej8pn2bv7edehbl FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_uok3tnpagf19irec4ikwl3lf FOREIGN KEY (entitled_user_groups_id)
      REFERENCES public.user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: virtual_app_entitled_users

-- DROP TABLE virtual_app_entitled_users;

CREATE TABLE virtual_app_entitled_users
(
  virtual_app_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT virtual_app_entitled_users_pkey PRIMARY KEY (virtual_app_id, entitled_users_id),
  CONSTRAINT fk_3ylbxctst8sc21bwkyq9w3yxl FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_etxla09jih7m3biqa1srgc8ml FOREIGN KEY (entitled_users_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: virtual_app_entitled_users_pks

-- DROP TABLE virtual_app_entitled_users_pks;

CREATE TABLE virtual_app_entitled_users_pks
(
  virtual_app_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_by5a2vtvlrv3ptsifocamtmyp FOREIGN KEY (virtual_app_id)
      REFERENCES public.virtual_app (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);









