DROP TABLE if exists vpc_ip_pool_ip_addresses;

DROP TABLE if exists vpc_ip_pool_vpc_ip_addresses_pks;

ALTER TABLE vpc_ip_pool
    ADD COLUMN vpc_name_id character varying(255);
    
-- ALTER TABLE public.vpc_ip_addresses DROP CONSTRAINT fk_assigned_user;

ALTER TABLE vpc_ip_addresses
    DROP COLUMN assigned_to;

ALTER TABLE vpc_ip_addresses
    ADD COLUMN assigned_to_id character varying(255) COLLATE pg_catalog."default";
    
ALTER TABLE vpc_ip_addresses
    ADD CONSTRAINT fk_assigned_user_id FOREIGN KEY (assigned_to_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE vpc_ip_addresses
    ADD COLUMN assigned_to_pk character varying(255);

ALTER TABLE vpc_ip_addresses
    ADD COLUMN vpc_ip_pool_id character varying(255);

ALTER TABLE vpc_ip_addresses
    ADD CONSTRAINT fk_vpc_ip_pool_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES public.vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

