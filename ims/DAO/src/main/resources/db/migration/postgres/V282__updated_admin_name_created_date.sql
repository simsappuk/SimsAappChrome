-- Update for Admin Name and Created Date. This should work for all new installations
-- Update for tenant name, cretaed date and last modofied date

UPDATE users SET firstname = 'HyperCloud', created_date = now(), last_modified_date = now() WHERE id = '402881834d9ee4d1014d9ee5d73f0014';
UPDATE tenant SET name = 'HyperCloud', created_date = now(), last_modified_date = now(), contact_name = 'HyperCloud Administrator' WHERE id = '402881834d9ee4d1014d9ee5d73f0010';