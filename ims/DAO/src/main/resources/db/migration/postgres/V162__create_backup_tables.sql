-- Table: backup

-- DROP TABLE backup;

CREATE TABLE backup
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
  description character varying(255),
  endpoint character varying(255),
  name character varying(255),
  status character varying(255),
  owner_id character varying(255),
  tenant_id character varying(255) NOT NULL,
  CONSTRAINT backup_pkey PRIMARY KEY (id),
  CONSTRAINT fk_id3cepfftamv262vibk24fmi2 FOREIGN KEY (tenant_id)
      REFERENCES tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pq0grml2fgin631aknyxa0te4 FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: backup_docker_servers

-- DROP TABLE backup_docker_servers;

CREATE TABLE backup_docker_servers
(
  backup_id character varying(255) NOT NULL,
  docker_servers_id character varying(255) NOT NULL,
  CONSTRAINT fk_eq9m5hfkbpe7growv5ne3x87r FOREIGN KEY (docker_servers_id)
      REFERENCES docker_server (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rp6qwwjv6lex3t65ywi7hsfx3 FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_eq9m5hfkbpe7growv5ne3x87r UNIQUE (docker_servers_id)
)
WITH (
  OIDS=FALSE
);


-- Table: backup_entitled_groups_pks

-- DROP TABLE backup_entitled_groups_pks;

CREATE TABLE backup_entitled_groups_pks
(
  backup_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_pd7uhp53rvur4hq2qnajhbek6 FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: backup_entitled_orgs

-- DROP TABLE backup_entitled_orgs;

CREATE TABLE backup_entitled_orgs
(
  backup_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT backup_entitled_orgs_pkey PRIMARY KEY (backup_id, entitled_orgs_id),
  CONSTRAINT fk_e067uhmhtxbw1m3lh2jid96cb FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_px3p6h9bempr4u49osvjvor1k FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: backup_entitled_user_groups

-- DROP TABLE backup_entitled_user_groups;

CREATE TABLE backup_entitled_user_groups
(
  backup_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT backup_entitled_user_groups_pkey PRIMARY KEY (backup_id, entitled_user_groups_id),
  CONSTRAINT fk_imjf3ffbfe30c359gcwkojvc0 FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_om4qcu6pq4kcjcppduxukh6ml FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: backup_entitled_users

-- DROP TABLE backup_entitled_users;

CREATE TABLE backup_entitled_users
(
  backup_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT backup_entitled_users_pkey PRIMARY KEY (backup_id, entitled_users_id),
  CONSTRAINT fk_ekmpnmi5agdqt3fg8olbhlvne FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_qh1jh9gm5r01iug7kb96epsio FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- Table: backup_entitled_users_pks

-- DROP TABLE backup_entitled_users_pks;

CREATE TABLE backup_entitled_users_pks
(
  backup_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_7oivh3iq488na9t1ik686bdrq FOREIGN KEY (backup_id)
      REFERENCES backup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
