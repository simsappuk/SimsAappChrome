-- 

-- DROP tables related to ip pool

drop table if exists ip_pool_quota_policy_pks;

drop table if exists ip_pool_quota_policies;

drop table if exists ip_pool_ips;

drop table if exists ip_pool_entitled_users_pks;

drop table if exists ip_pool_entitled_users;

drop table if exists ip_pool_entitled_user_groups;

drop table if exists ip_pool_entitled_orgs;

drop table if exists ip_pool_entitled_groups_pks;

ALTER TABLE public.ip DROP CONSTRAINT fk_ip_pool;

drop table if exists ip_pool;

CREATE TABLE ip_pool
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  deleted boolean,
  inactive boolean,
  lock_version integer,
  uuid character varying(255),
  provider_id character varying(255),
  private_ip character varying(255),
  public_ip character varying(255),
  used_by_id character varying(255),
  ip_status character varying(50),
  owner_id character varying(255),
  owner_pk character varying(255),
  name character varying(255),
  CONSTRAINT ip_pool_pkey PRIMARY KEY (id)
)

