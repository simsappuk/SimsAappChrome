ALTER TABLE virtual_private_cloud ADD COLUMN cloudtype character varying(255);
ALTER TABLE virtual_private_cloud ALTER COLUMN cloudtype
SET DEFAULT 'PUBLIC';

--update existing entries
UPDATE virtual_private_cloud SET cloudtype = 'ENTERPRISE' where description LIKE 'mapping%';

UPDATE virtual_private_cloud SET cloudtype = 'PUBLIC' where description IS NULL;
