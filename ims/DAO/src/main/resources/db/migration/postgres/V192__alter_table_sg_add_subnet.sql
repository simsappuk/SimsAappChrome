-- Author: Intesar Mohammed
-- Table: security_group

ALTER TABLE security_group
    ADD COLUMN subnet_id character varying(255);

ALTER TABLE security_group ADD CONSTRAINT fk_sg_subnet_id FOREIGN KEY (subnet_id)
      REFERENCES subnet (id);

ALTER TABLE security_group
    ADD COLUMN subnet_ref_id character varying(255);