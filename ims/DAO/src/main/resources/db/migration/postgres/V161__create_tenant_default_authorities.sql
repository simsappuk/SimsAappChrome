CREATE TABLE tenant_default_authorities (
    tenant_id character varying(255) NOT NULL,
    default_authorities character varying(255),
    CONSTRAINT fk_tenant_default_authorities_tenant_id FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);

CREATE TABLE tenant_default_group_ids (
    tenant_id character varying(255) NOT NULL,
    group_id character varying(255),
    CONSTRAINT fk_tenant_default_groups_tenant_id FOREIGN KEY (tenant_id) REFERENCES tenant(id),
    CONSTRAINT fk_tenant_default_groups_group_id FOREIGN KEY (group_id) REFERENCES user_group(id)
);