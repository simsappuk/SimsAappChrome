-- author Mohammed Shoukath Ali
-- alter TABLE blueprint;
-- add column visibility

ALTER TABLE blueprint ADD COLUMN visibility character varying(255);
UPDATE blueprint SET visibility = 'EDITABLE' WHERE editable = 'TRUE';
UPDATE blueprint SET visibility = 'READABLE' WHERE editable = 'FALSE';
