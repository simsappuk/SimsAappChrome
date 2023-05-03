-- create table

CREATE TABLE deployment_profile
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
  blueprint character varying(255),
  cluster character varying(255),
  cron_date character varying(255),
  description text,
  lease_time character varying(255),
  name character varying(255),
  next_cron_date timestamp without time zone,
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT deployment_profile_pkey PRIMARY KEY (id),
  CONSTRAINT fk_4fqyrvdevpg8lvavbaeca84ti FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_htboaujebdd9a9eg728oao1bi FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE deployment_profile_entitled_groups_pks
(
  deployment_profile_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_9f1ug2tolpho8pmc7irqih79i FOREIGN KEY (deployment_profile_id)
      REFERENCES deployment_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE deployment_profile_entitled_orgs
(
  deployment_profile_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT deployment_profile_entitled_orgs_pkey PRIMARY KEY (deployment_profile_id, entitled_orgs_id),
  CONSTRAINT fk_2q1c3huhd43wgvaeif4tjb4b2 FOREIGN KEY (deployment_profile_id)
      REFERENCES deployment_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pwhvw1v40xmbgxvl6upsaibxh FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE deployment_profile_entitled_user_groups
(
  deployment_profile_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT deployment_profile_entitled_user_groups_pkey PRIMARY KEY (deployment_profile_id, entitled_user_groups_id),
  CONSTRAINT fk_3rh4te6wj4jp9231p6hp23hob FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tr2q3slfj60mrq8inp8egnani FOREIGN KEY (deployment_profile_id)
      REFERENCES deployment_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE deployment_profile_entitled_users
(
  deployment_profile_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT deployment_profile_entitled_users_pkey PRIMARY KEY (deployment_profile_id, entitled_users_id),
  CONSTRAINT fk_137dcbqesk8xks0fbghtkk6o6 FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ex8nj8webr5hhlylxgxnk1gve FOREIGN KEY (deployment_profile_id)
      REFERENCES deployment_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE deployment_profile_entitled_users_pks
(
  deployment_profile_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_etmxgg8y8x0bcpa7bg5js6d0q FOREIGN KEY (deployment_profile_id)
      REFERENCES deployment_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
