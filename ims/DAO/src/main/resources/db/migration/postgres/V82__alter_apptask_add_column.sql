-- Table: app_task
-- author Mohammed Shoukath Ali
-- ADD Column 


ALTER TABLE app_task ADD COLUMN provision character varying(255);
ALTER TABLE app_task ADD COLUMN expiration_date timestamp without time zone;
