-- Table: data_center and provision

-- Adding a new Column Lease time

ALTER TABLE data_center ADD COLUMN lease_time character varying(100);
ALTER TABLE provision ADD COLUMN expiration_date timestamp without time zone;