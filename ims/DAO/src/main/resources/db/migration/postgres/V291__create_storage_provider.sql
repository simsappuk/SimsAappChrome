--create storage service
DROP TABLE if exists storage_provider;
DROP TABLE if exists storage_provider_mapping;
		
CREATE TABLE storage_provider (
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
	name						CHARACTER VARYING(255) NOT NULL,
	type                        CHARACTER VARYING(255) NOT NULL,
	capacity					CHARACTER VARYING(255),
	free						CHARACTER VARYING(255),
	isSelected					BOOLEAN,
	allocation_type				CHARACTER VARYING(255),
	storage_service_id			CHARACTER VARYING(255),
    availability_zone_id        CHARACTER VARYING(255),
	provider_id           		CHARACTER VARYING(255)
   );
   
ALTER TABLE ONLY storage_provider
    ADD CONSTRAINT storage_provider_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY storage_provider
    ADD CONSTRAINT fk_storage_provider_to_registry_account FOREIGN KEY (provider_id) REFERENCES registry_account(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
	

ALTER TABLE ONLY storage_provider
    ADD CONSTRAINT fk_storage_provider_to_registry_account2 FOREIGN KEY (availability_zone_id) REFERENCES registry_account(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ONLY storage_provider
    ADD CONSTRAINT fk_storage_provider_to_registry_account3 FOREIGN KEY (storage_service_id) REFERENCES registry_account(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
		
	
CREATE TABLE storage_provider_mapping (
  id 						CHARACTER VARYING(255) NOT NULL,
  created_by 				CHARACTER VARYING(255),
  created_date 				TIMESTAMP WITHOUT TIME ZONE,
  last_modified_by 			CHARACTER VARYING(255),
  last_modified_date 		TIMESTAMP WITHOUT TIME ZONE,
  deleted 					BOOLEAN,
  inactive 					BOOLEAN,
  lock_version 				INTEGER,
  owner_pk 					CHARACTER VARYING(255),
  uuid 						CHARACTER VARYING(255),
  owner_id                  CHARACTER VARYING(255),
  name						CHARACTER VARYING(255),
  rp_type					CHARACTER VARYING(100),	
  storage_provider_id		CHARACTER VARYING(255),
  resource_pool_id      	CHARACTER VARYING(255)
);
	
ALTER TABLE ONLY storage_provider_mapping
    ADD CONSTRAINT storage_provider_mapping_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY storage_provider_mapping
    ADD CONSTRAINT fk_storage_provider_mapping_to_storage_provider FOREIGN KEY (storage_provider_id) REFERENCES storage_provider(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ONLY storage_provider_mapping
    ADD CONSTRAINT fk_storage_provider_mapping_to_resource_pool FOREIGN KEY (resource_pool_id) REFERENCES resource_pool(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;