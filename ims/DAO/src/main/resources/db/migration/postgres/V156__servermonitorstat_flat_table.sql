-- Author: Mohammed Shoukath Ali


-- Table: server_monitor_stat_flat

-- DROP TABLE server_monitor_stat_flat;

CREATE TABLE server_monitor_stat_flat
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  containers integer,
  cores integer,
  dt timestamp without time zone,
  images integer,
  proc_cpu_per double precision,
  proc_mem_resident double precision,
  server_id character varying(255),
  total_disk_usage_percent double precision,
  total_memory double precision,
  CONSTRAINT server_monitor_stat_flat_pkey PRIMARY KEY (id)
);

-- Table: server_monitor_stat_flat_disk_statuses

-- DROP TABLE server_monitor_stat_flat_disk_statuses;

CREATE TABLE server_monitor_stat_flat_disk_statuses
(
  server_monitor_stat_flat_id character varying(255) NOT NULL,
  avail character varying(255),
  file_system character varying(255),
  mounted_on character varying(255),
  size_ character varying(255),
  type_ character varying(255),
  disk_perc double precision,
  used character varying(255),
  CONSTRAINT fk_dofdf9eh2g1nxsdmw9in3i6nn FOREIGN KEY (server_monitor_stat_flat_id)
      REFERENCES public.server_monitor_stat_flat (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


