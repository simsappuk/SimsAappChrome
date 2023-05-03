-- Author: Intesar Mohammed
-- Table: rule


ALTER TABLE rule add COLUMN rule_id character varying(255);
ALTER TABLE rule add COLUMN address_family character varying(255);
ALTER TABLE rule add COLUMN ip character varying(255);
ALTER TABLE rule add COLUMN port character varying(255);





