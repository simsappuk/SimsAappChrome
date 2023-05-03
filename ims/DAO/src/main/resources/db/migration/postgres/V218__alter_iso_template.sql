-- Author: Intesar Mohammed

-- Table: iso_template

ALTER TABLE iso_template add COLUMN os_type  character varying(255);
ALTER TABLE iso_template add COLUMN username  character varying(255);
ALTER TABLE iso_template add COLUMN password  character varying(255);
ALTER TABLE iso_template add COLUMN agent_install  boolean ;
