CREATE TABLE folders(
id character varying(255),
name character varying(255),
folder_id character varying(255),
owner_id character varying(255),
entitle_owner character varying(255),
entitle_read_only character varying(255),
entitle_edit character varying(255),
created_date DATE,
modified_date DATE,
CONSTRAINT folders_pkey PRIMARY KEY (id)
);