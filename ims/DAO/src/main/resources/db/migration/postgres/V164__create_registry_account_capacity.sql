-- Table: public.capacity
CREATE TABLE capacity
(
    id character varying(255)  NOT NULL,
	cores integer,
    memory double precision,
    disk_memory double precision,
    created_by character varying(255),
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    created_date timestamp without time zone,
    CONSTRAINT pk_capacity_id PRIMARY KEY (id)
);
	
-- Table: registry_account_capacity
CREATE TABLE registry_account_capacity
(
    registry_account_id character varying(255)  NOT NULL,
    capacity_id character varying(255)  NOT NULL,
    CONSTRAINT pk_registry_account_id_capacity_id PRIMARY KEY (registry_account_id, capacity_id),
    CONSTRAINT fk_capacity_id FOREIGN KEY (capacity_id)
        REFERENCES capacity (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_registry_account_id FOREIGN KEY (registry_account_id)
        REFERENCES registry_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE user_group_users_admin
(
    user_group_id character varying(255)  NOT NULL,
    user_id character varying(255)  NOT NULL,
    CONSTRAINT pk_user_group_id_user_id PRIMARY KEY (user_group_id, user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_group_id FOREIGN KEY (user_group_id)
        REFERENCES user_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);