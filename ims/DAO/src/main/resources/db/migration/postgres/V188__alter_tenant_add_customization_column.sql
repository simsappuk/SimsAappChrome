-- Table: tenant

-- Adding a new customization columns

ALTER TABLE tenant ADD COLUMN title character varying(255);
ALTER TABLE tenant ADD COLUMN logo character varying(512);
ALTER TABLE tenant ADD COLUMN color character varying(255);