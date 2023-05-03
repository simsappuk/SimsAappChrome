ALTER TABLE subnet DROP COLUMN network_acl_id;
ALTER TABLE subnet DROP COLUMN vlan_id;

ALTER TABLE subnet
    ADD COLUMN virtual_network_id character varying(255);

ALTER TABLE subnet
    ADD CONSTRAINT subnet_virtual_network_id_fk FOREIGN KEY (virtual_network_id)
    REFERENCES virtual_network (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
    
CREATE TABLE subnet_network_acls
(
    subnet_id character varying(255) NOT NULL,
    network_acls_id character varying(255) NOT NULL,
    CONSTRAINT subnet_network_acls_pkey PRIMARY KEY (subnet_id, network_acls_id),
    CONSTRAINT fk_subnet_network_acls_network_acl_id FOREIGN KEY (network_acls_id)
        REFERENCES network_acl (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_subnet_network_acls_subnet_id FOREIGN KEY (subnet_id)
        REFERENCES subnet (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


ALTER TABLE virtual_network
    ADD COLUMN subnet_id character varying(255);
    