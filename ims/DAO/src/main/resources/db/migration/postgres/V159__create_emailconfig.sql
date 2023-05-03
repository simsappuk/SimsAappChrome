-- Table: public.email_config

-- DROP TABLE email_config;

CREATE TABLE email_config
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
  mail_bcc character varying(255),
  mail_failure_to character varying(255),
  mail_from character varying(255),
  mail_host character varying(255),
  mail_password character varying(255),
  mail_port character varying(255),
  mail_prop_auth character varying(255),
  mail_prop_socker_factory_class character varying(255),
  mail_prop_socker_factory_port character varying(255),
  mail_prop_socket_factory_fallback character varying(255),
  mail_prop_start_tls_enable character varying(255),
  mail_to_test character varying(255),
  mail_user_name character varying(255),
  owner_id character varying(255),
  CONSTRAINT email_config_pkey PRIMARY KEY (id),
  CONSTRAINT fk_imndvmi2nsyf1iqabw9yogslj FOREIGN KEY (owner_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

