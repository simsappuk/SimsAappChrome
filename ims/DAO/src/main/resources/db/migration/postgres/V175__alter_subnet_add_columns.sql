ALTER TABLE subnet
    ADD COLUMN parent_interface character varying(255);

ALTER TABLE subnet
    ADD COLUMN vlan_id character varying(255);
	
ALTER TABLE subnet
    ADD COLUMN subnet_id character varying(255);

ALTER TABLE subnet
    ADD COLUMN ip_address character varying(255);

ALTER TABLE subnet
    ADD COLUMN dhcp character varying(255);

ALTER TABLE subnet
    ADD COLUMN from_ip_range character varying(255);

ALTER TABLE subnet
    ADD COLUMN to_ip_range character varying(255);	
	
ALTER TABLE subnet
    ADD COLUMN dns_servers character varying(255);

ALTER TABLE subnet
    ADD COLUMN network_acl_id character varying(255);
	
ALTER TABLE subnet
    ADD COLUMN vpc_id character varying(255);	

ALTER TABLE subnet
    ADD CONSTRAINT subnet_network_acl_id_fk FOREIGN KEY (network_acl_id)
    REFERENCES network_acl (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE subnet
    ADD CONSTRAINT fk_subnet_vpc FOREIGN KEY (vpc_id)
    REFERENCES virtual_private_cloud (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;