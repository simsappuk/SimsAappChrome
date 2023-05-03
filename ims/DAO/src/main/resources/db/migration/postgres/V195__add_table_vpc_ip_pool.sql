-- @author Faizan Mohammad

-- Table: vpc_ip_pool

CREATE TABLE vpc_ip_pool
(
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    name character varying(255),
    description character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    virtual_private_cloud_id character varying(255),
    CONSTRAINT vpc_ip_pool_pkey PRIMARY KEY (id),
    CONSTRAINT fk_na_virtual_private_cloud FOREIGN KEY (virtual_private_cloud_id)
        REFERENCES virtual_private_cloud (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vpc_ip_pool_owner FOREIGN KEY (owner_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vpc_tenant FOREIGN KEY (tenant_id)
        REFERENCES tenant (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
	
-- Table: vpc_ip_pool_entitled_groups_pks

CREATE TABLE vpc_ip_pool_entitled_groups_pks
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255),
    CONSTRAINT fk_vpc_ip_pool_entitled_groups_pks_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_entitled_orgs

CREATE TABLE vpc_ip_pool_entitled_orgs
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL,
    CONSTRAINT vpc_ip_pool_entitled_orgs_pkey PRIMARY KEY (vpc_ip_pool_id, entitled_orgs_id),
    CONSTRAINT fk_vpc_ip_pool_entitled_orgs FOREIGN KEY (entitled_orgs_id)
        REFERENCES organization (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vpc_ip_pool_entitled_orgs_netid FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_entitled_user_groups

CREATE TABLE vpc_ip_pool_entitled_user_groups
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL,
    CONSTRAINT vpc_ip_pool_entitled_user_groups_pkey PRIMARY KEY (vpc_ip_pool_id, entitled_user_groups_id),
    CONSTRAINT fk_vpc_ip_pool_entitled_user_groups_id FOREIGN KEY (entitled_user_groups_id)
        REFERENCES user_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vpc_ip_pool_entitled_user_groups_netid FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_entitled_users

CREATE TABLE vpc_ip_pool_entitled_users
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL,
    CONSTRAINT vpc_ip_pool_entitled_users_pkey PRIMARY KEY (vpc_ip_pool_id, entitled_users_id),
    CONSTRAINT fk_vpc_ip_pool_entitled_users_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vpc_ip_pool_entitled_users_users_id FOREIGN KEY (entitled_users_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_entitled_users_pks

CREATE TABLE vpc_ip_pool_entitled_users_pks
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255),
    CONSTRAINT vpc_ip_pool_entitled_users_pks_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_addresses

CREATE TABLE vpc_ip_addresses
(
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    public_ip character varying(50),
    private_ip character varying(255),
    ip_address_id character varying(255),
    status character varying(50),
    assigned_to character varying(255),
    vpc_ip_pool_pk character varying(255),
    owner_id character varying(255),
    CONSTRAINT vpc_ip_addresses_pkey PRIMARY KEY (id),
    CONSTRAINT fk_vpc_owner FOREIGN KEY (owner_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_vpc_ip_addresses

CREATE TABLE vpc_ip_pool_ip_addresses
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    ip_addresses_id character varying(255) NOT NULL,
    CONSTRAINT uk_vpc_ip_addresses_id UNIQUE (ip_addresses_id),
    CONSTRAINT fk_vpc_ip_addresses_id FOREIGN KEY (ip_addresses_id)
        REFERENCES vpc_ip_addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vpc_ip_pool_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: vpc_ip_pool_vpc_ip_addresses_pks

CREATE TABLE vpc_ip_pool_vpc_ip_addresses_pks
(
    vpc_ip_pool_id character varying(255) NOT NULL,
    vpc_ip_addresses_pks character varying(255),
    CONSTRAINT vpc_ip_pool_pks_id FOREIGN KEY (vpc_ip_pool_id)
        REFERENCES vpc_ip_pool (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);