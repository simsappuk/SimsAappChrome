-- author Faizan Mohammad
-- Create Tables for Machine Images


CREATE TABLE machine_image
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
  name character varying(255),
  cloud character varying(255),
  image_id character varying(255),
  description character varying(500),
  version character varying(255),
  status character varying(255),
  region character varying(255),
  image_login_user character varying(255),
  publisher character varying(255),
  image_size character varying(255),
  os_family character varying(255),
  owner_id character varying(255),
  CONSTRAINT machine_image_pkey PRIMARY KEY (id)
);


