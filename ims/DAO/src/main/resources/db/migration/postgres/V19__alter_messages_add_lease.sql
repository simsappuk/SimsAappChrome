-- Table: INBOX_MESSAGE

-- Adding a new Column LEASE_TIME

ALTER TABLE INBOX_MESSAGE ADD COLUMN LEASE_TIME character varying(255);
