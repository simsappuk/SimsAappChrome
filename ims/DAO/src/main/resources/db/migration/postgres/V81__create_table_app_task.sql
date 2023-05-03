-- author Mohammed Shoukath Ali
-- Table: app_task

-- DROP TABLE app_task;

CREATE TABLE app_task
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
  app_name character varying(255),
  app_task_priority character varying(255),
  app_task_status character varying(255),
  blueprint character varying(255),
  containers character varying(255),
  data_center_id character varying(255),
  mem_need character varying(255),
  process_start_time timestamp without time zone,
  owner_id character varying(255),
  CONSTRAINT app_task_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ib0nd87b2gp011ixqdk533iwb FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE app_task
  OWNER TO dchq;
