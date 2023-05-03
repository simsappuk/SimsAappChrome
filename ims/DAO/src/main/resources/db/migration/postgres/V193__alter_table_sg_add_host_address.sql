-- Author: Intesar Mohammed
-- Table: security_group

ALTER TABLE security_group
    ADD COLUMN alias_address character varying(255);

ALTER TABLE security_group
    ADD COLUMN alias_hosts character varying(512);
