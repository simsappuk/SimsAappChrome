-- Table: node_profile and provision

-- Adding a new Column Lease time

ALTER TABLE node_profile ADD COLUMN time_ character varying(100);
ALTER TABLE node_profile ADD COLUMN next_cron_date timestamp without time zone;