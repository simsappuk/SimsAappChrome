-- author Mohammed Shoukath Ali
-- alter TABLE build;
-- add columns

ALTER TABLE build ADD COLUMN last_comit_date timestamp without time zone;
ALTER TABLE build ADD COLUMN monitor_repo boolean;

UPDATE build SET monitor_repo = false;

