-- Author: Mohammed Luqman Shareef
-- Table: ip_task

-- DROP TABLE ip_task;

CREATE TABLE ip_task
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
  ip_task_status character varying(255),
  ip_task_type character varying(255),
  ended timestamp without time zone,
  error_msg text,
  note text,
  retry_attempts integer,
  scheduled timestamp without time zone,
  started timestamp without time zone,
  waiting_on character varying(255),
  owner_id character varying(255),
  ip_id character varying(255)
);