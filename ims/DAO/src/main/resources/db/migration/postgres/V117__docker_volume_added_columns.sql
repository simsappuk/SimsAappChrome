ALTER table docker_volume add column entitlement character varying(255);

ALTER table docker_volume add column entitlement_type character varying(255);

ALTER table docker_volume add column status character varying(255);
ALTER table docker_volume add column tenant_id character varying(255);
ALTER table docker_volume add column tenant_pk character varying(255);

ALTER TABLE docker_volume_task_dynamic_attributes RENAME COLUMN docker_server_task_id TO docker_volume_task_id;
  
  
  -- Table: docker_volume_entitled_groups_pks

-- DROP TABLE docker_volume_entitled_groups_pks;

CREATE TABLE docker_volume_entitled_groups_pks
(
  docker_volume_id character varying(255) NOT NULL,
  entitled_groups_pks character varying(255),
  CONSTRAINT fk_kwxlk08akfmpthcgoh6t6dng6 FOREIGN KEY (docker_volume_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_volume_entitled_groups_pks
  OWNER TO dchq;

  
  -- Table: docker_volume_entitled_orgs

-- DROP TABLE docker_volume_entitled_orgs;

CREATE TABLE docker_volume_entitled_orgs
(
  docker_volume_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT docker_volume_entitled_orgs_pkey PRIMARY KEY (docker_volume_id, entitled_orgs_id),
  CONSTRAINT fk_q1y7lr59jl1s0qyyysgtgal7q FOREIGN KEY (entitled_orgs_id)
      REFERENCES organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_swcec0xie4qab4mdgk8ee98aa FOREIGN KEY (docker_volume_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_volume_entitled_orgs
  OWNER TO dchq;

  
  -- Table: docker_volume_entitled_user_groups

-- DROP TABLE docker_volume_entitled_user_groups;

CREATE TABLE docker_volume_entitled_user_groups
(
  docker_volume_id character varying(255) NOT NULL,
  entitled_user_groups_id character varying(255) NOT NULL,
  CONSTRAINT docker_volume_entitled_user_groups_pkey PRIMARY KEY (docker_volume_id, entitled_user_groups_id),
  CONSTRAINT fk_5e70ita1a6kvkgxxvcn1drb20 FOREIGN KEY (docker_volume_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_l73j7ryej7hjromcekf1prr7j FOREIGN KEY (entitled_user_groups_id)
      REFERENCES user_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_volume_entitled_user_groups
  OWNER TO dchq;

  
  -- Table: docker_volume_entitled_users

-- DROP TABLE docker_volume_entitled_users;

CREATE TABLE docker_volume_entitled_users
(
  docker_volume_id character varying(255) NOT NULL,
  entitled_users_id character varying(255) NOT NULL,
  CONSTRAINT docker_volume_entitled_users_pkey PRIMARY KEY (docker_volume_id, entitled_users_id),
  CONSTRAINT fk_3pdg0klbpfi2ateteq4neendv FOREIGN KEY (entitled_users_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_dc7rrny8emgdtgrb6raxuh2ay FOREIGN KEY (docker_volume_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_volume_entitled_users
  OWNER TO dchq;

  
  -- Table: docker_volume_entitled_users_pks

-- DROP TABLE docker_volume_entitled_users_pks;

CREATE TABLE docker_volume_entitled_users_pks
(
  docker_volume_id character varying(255) NOT NULL,
  entitled_users_pks character varying(255),
  CONSTRAINT fk_hvw8qstmewxf9ncse7n84saju FOREIGN KEY (docker_volume_id)
      REFERENCES docker_volume (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE docker_volume_entitled_users_pks
  OWNER TO dchq;


  
 