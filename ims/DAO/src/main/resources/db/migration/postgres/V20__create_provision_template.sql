-- Table: provision_template

-- DROP TABLE provision_template;

CREATE TABLE provision_template
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  provision_id character varying(255),
  template text,
  CONSTRAINT provision_template_pkey PRIMARY KEY (id)
)