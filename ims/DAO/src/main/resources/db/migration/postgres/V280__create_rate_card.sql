CREATE TABLE rate_card (
    id                          CHARACTER VARYING(255) NOT NULL,
    created_by                  CHARACTER VARYING(255),
    created_date                TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by            CHARACTER VARYING(255),
    last_modified_date          TIMESTAMP WITHOUT TIME ZONE,
    deleted                     BOOLEAN,
    inactive                    BOOLEAN,
    lock_version                INTEGER,
    owner_pk                    CHARACTER VARYING(255),
    uuid                        CHARACTER VARYING(255),
    owner_id                    CHARACTER VARYING(255),
    type                        CHARACTER VARYING(255) NOT NULL,
    provider_id                 CHARACTER VARYING(255),
    availability_zone_id        CHARACTER VARYING(255),
    discount                    NUMERIC (5, 2),
    price_vcpu_hour             NUMERIC (5, 2),
    price_mem_gb_hour           NUMERIC (5, 2),
    price_disk_gb_hour          NUMERIC (5, 2),
    price_virtual_network_hour  NUMERIC (5, 2),
    price_plugin_hour           NUMERIC (5, 2),
    tenant_id                   CHARACTER VARYING(255),
    markup                      NUMERIC (5, 2),
    resource_pool_id            CHARACTER VARYING(255)
   );


ALTER TABLE ONLY rate_card
    ADD CONSTRAINT rate_card_pkey PRIMARY KEY (id);

ALTER TABLE ONLY rate_card
    ADD CONSTRAINT fk_rate_card_to_registry_account FOREIGN KEY (provider_id) REFERENCES registry_account(id);

ALTER TABLE ONLY rate_card
    ADD CONSTRAINT fk_rate_card_to_registry_account2 FOREIGN KEY (availability_zone_id) REFERENCES registry_account(id);

ALTER TABLE ONLY rate_card
    ADD CONSTRAINT fk_rate_card_to_resource_pool FOREIGN KEY (resource_pool_id) REFERENCES resource_pool(id);

ALTER TABLE ONLY rate_card
    ADD CONSTRAINT fk_rate_card_to_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id);

