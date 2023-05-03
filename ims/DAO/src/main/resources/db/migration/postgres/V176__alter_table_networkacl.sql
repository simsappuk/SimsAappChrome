-- Author: Mohammed Shoukath Ali
-- Table: network_acl

ALTER TABLE security_group
    ADD COLUMN virtual_private_cloud_id character varying(255);

ALTER TABLE security_group ADD CONSTRAINT fk_b6gpajyaijjvdk4k9lt9wt8914 FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id);

ALTER TABLE security_group add COLUMN description character varying(255);
ALTER TABLE security_group add COLUMN firewal_sg_name character varying(255);


ALTER TABLE network_acl
    ADD COLUMN virtual_private_cloud_id character varying(255);

ALTER TABLE network_acl ADD CONSTRAINT fk_na_virtual_private_cloud FOREIGN KEY (virtual_private_cloud_id)
      REFERENCES virtual_private_cloud (id);

ALTER TABLE security_group drop  COLUMN if exists vpc;
ALTER TABLE security_group drop COLUMN if exists vpc_name;



