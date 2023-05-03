ALTER TABLE subnet
    ADD COLUMN vlan_id character varying(255);

ALTER TABLE subnet
    ADD COLUMN vpc_name_id character varying(255);