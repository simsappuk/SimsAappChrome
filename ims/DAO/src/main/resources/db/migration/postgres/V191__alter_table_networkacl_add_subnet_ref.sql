-- Author: Intesar Mohammed
-- Table: network_acl

ALTER TABLE network_acl
    ADD COLUMN subnet_ref_id character varying(255);
