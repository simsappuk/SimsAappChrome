-- Table: data_center_stats

-- DROP TABLE data_center_stats;

CREATE TABLE data_center_stats
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
  hour_of_day integer,
  servers_count integer,
  cost double precision,
  planned_servers integer,
  planned_servers_cost double precision,
  on_demand_servers integer,
  on_demand_servers_cost double precision,
  data_center_id character varying(255),
  owner_id character varying(255),
  stats_date timestamp without time zone,
  CONSTRAINT data_center_stats_stat_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE data_center_stats
  OWNER TO dchq;
