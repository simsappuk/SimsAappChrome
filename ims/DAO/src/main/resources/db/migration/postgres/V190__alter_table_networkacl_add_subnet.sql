-- Author: Intesar Mohammed
-- Table: network_acl

ALTER TABLE network_acl
    ADD COLUMN subnet_id character varying(255);

ALTER TABLE network_acl ADD CONSTRAINT fk_na_subnet_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id);
