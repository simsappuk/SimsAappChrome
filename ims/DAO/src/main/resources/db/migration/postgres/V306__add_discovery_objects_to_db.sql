-- Author:Sanjay Mahara
-- Targeting information store for private cloud analytics
-- Table: vm and volume part of PCA discovery table for vm and volume assessment

CREATE TABLE discovery_info
(
  id                                 CHARACTER VARYING(255) NOT NULL,
  description                        CHARACTER VARYING(1024),
  docker_server_status               CHARACTER VARYING(255),
  endpoint                           CHARACTER VARYING(255),
  endpoint_type                      CHARACTER VARYING(255),
  hardware_id                        CHARACTER VARYING(255),
  recommended_hardware_id            CHARACTER VARYING(255),
  host_or_ip                         CHARACTER VARYING(255),
  image_id                           CHARACTER VARYING(255),
  image_name                         CHARACTER VARYING(255),
  network                            CHARACTER VARYING(255) ,
  network_id                         CHARACTER VARYING(255),
  network_name                       CHARACTER VARYING(255),
  node_id                            CHARACTER VARYING(255),
  storage_id                         CHARACTER VARYING(255),
  affinity                           CHARACTER VARYING(255),
  operating_system                   CHARACTER VARYING(255),
  resource_pool_id                   CHARACTER VARYING(255),
  az_id                              CHARACTER VARYING(255),
  vm_id                              CHARACTER VARYING(255) NOT NULL,
  provider_type                      CHARACTER VARYING(255) NOT NULL,
  name                               CHARACTER VARYING(255) NOT NULL,
  ip_address                         CHARACTER VARYING(255) ,
  status                             CHARACTER VARYING(255) ,
  cpu                                INTEGER ,
  cpu_percent                        NUMERIC(5,2) ,
  cpu_peak_usage                     BIGINT ,
  memory                             BIGINT ,
  memory_percent                     NUMERIC(5,2) ,
  memory_peek_usage                  BIGINT ,
  disk                               BIGINT ,
  disk_percent                       NUMERIC(5,2) ,
  disk_iops                          BIGINT,
  ignore                             BOOLEAN ,
  discovery_type                     CHARACTER VARYING(255),
  discovery_status                   CHARACTER VARYING(255),
  start_time 						 TIMESTAMP WITHOUT TIME ZONE,
  stop_time 						 TIMESTAMP WITHOUT TIME ZONE,
  up_time 							 CHARACTER VARYING(255),
  cluster 							 CHARACTER VARYING(255),
  datacenter						 CHARACTER VARYING(255),
  os_family 						 CHARACTER VARYING(255),
  os_kernal 						 CHARACTER VARYING(255),
  task_id                            CHARACTER VARYING(255),
  profile_id                         CHARACTER VARYING(255),
  deleted                            BOOLEAN,
  inactive                           BOOLEAN,
  lock_version                       INTEGER,
  uuid                               CHARACTER VARYING(255),
  owner_pk                           CHARACTER VARYING(255),
  owner_id                           CHARACTER VARYING(255),
  tenant_id                          CHARACTER VARYING(255),
  tenant_pk                          CHARACTER VARYING(255),
  error_logs                         text,
  created_by                         CHARACTER VARYING(255),
  created_date                       TIMESTAMP WITHOUT TIME ZONE,
  last_modified_by                   CHARACTER VARYING(255),
  last_modified_date                 TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT pca_discovery_pkey      PRIMARY KEY (id)
 );
 
CREATE TABLE discovery_task_info(
 id 										CHARACTER VARYING(255) NOT NULL,
 task_type 									CHARACTER VARYING(255) NOT NULL,
 profile_id                                 CHARACTER VARYING(255),
 discovery_start_date                       TIMESTAMP WITHOUT TIME ZONE,
 discovery_end_date                         TIMESTAMP WITHOUT TIME ZONE,
 resource_pool_id 							CHARACTER VARYING(255),
 az_id 										CHARACTER VARYING(255),
 provider_type 								CHARACTER VARYING(255),
 days_count   								INTEGER ,
 discovery_type 							CHARACTER VARYING(255) NOT NULL,
 discover_count 							INTEGER,
 created_by 								CHARACTER VARYING(255),
 created_date 								TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							CHARACTER VARYING(255),
 last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_discovery_taskinfo_pkey  	PRIMARY KEY (id)
  
); 

CREATE TABLE discovery_task (
    id                                      CHARACTER VARYING(255) NOT NULL,
    task_info_id                            CHARACTER VARYING(255) NOT NULL,
    parent_task_id                          CHARACTER VARYING(255),
    profile_id                              CHARACTER VARYING(255),
    discovery_task_status 					CHARACTER VARYING(255),
    discovery_task_type 					CHARACTER VARYING(255),
    ended 									TIMESTAMP WITHOUT TIME ZONE,
    error_msg 								text,
    note 									text,
    retry_attempts 							INTEGER,
    scheduled 								TIMESTAMP WITHOUT TIME ZONE,
    started 								TIMESTAMP WITHOUT TIME ZONE,
    waiting_on 								CHARACTER VARYING(255),
    owner_id 								CHARACTER VARYING(255),
    tenant_id                               CHARACTER VARYING(255),
    resourcepool_id 						CHARACTER VARYING(255) ,
    az_id 									CHARACTER VARYING(255),
    last_run 								BOOLEAN,
    discovery_type 							CHARACTER VARYING(255) NOT NULL,
    created_by                              CHARACTER VARYING(255),
    created_date                            TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by                        CHARACTER VARYING(255),
    last_modified_date                      TIMESTAMP WITHOUT TIME ZONE,
    deleted                                 BOOLEAN,
    inactive  								BOOLEAN,
    lock_version 							INTEGER,
    owner_pk 								CHARACTER VARYING(255),
    tenant_pk                               CHARACTER VARYING(255),
    uuid 									CHARACTER VARYING(255),
    CONSTRAINT pca_discovery_task_pkey  	PRIMARY KEY (id),
    FOREIGN KEY (task_info_id) REFERENCES discovery_task_info(id)
);

CREATE TABLE discovery_volume
(
    id 										CHARACTER VARYING(255)  NOT NULL,
    endpoint 								CHARACTER VARYING(255), 
    endpoint_type 							CHARACTER VARYING(255), 
    name 									CHARACTER VARYING(255),
    volume_id 								CHARACTER VARYING(255),
    status 									CHARACTER VARYING(255) ,
    rw 										CHARACTER VARYING(255) ,
    description 							CHARACTER VARYING(1024), 
    datacenter 								CHARACTER VARYING(255),
    disk_name 								CHARACTER VARYING(255) ,
    disk_id 								CHARACTER VARYING(255) ,
    disk_type 								CHARACTER VARYING(255),
    disk_size 								BIGINT,
    disk_size_text 							CHARACTER VARYING(255),
    attached 								BOOLEAN,
    attached_vm 							CHARACTER VARYING(255), 
    mount_point 							CHARACTER VARYING(255) ,
    scope 									CHARACTER VARYING(255) ,
    source 									CHARACTER VARYING(255),
    destination 							CHARACTER VARYING(255),
    containers 								CHARACTER VARYING(255) ,
    driver 									CHARACTER VARYING(255) ,
    mode 									CHARACTER VARYING(255) ,
    propagation 							CHARACTER VARYING(255), 
    resourcepool_id 						CHARACTER VARYING(255), 
    cluster_id 								CHARACTER VARYING(255),
    cluster_name 							CHARACTER VARYING(255), 
    mounted_on 								CHARACTER VARYING(255) ,
    host_ip 								CHARACTER VARYING(255) ,
    entitlement 							CHARACTER VARYING(255),
    entitlement_type 						CHARACTER VARYING(255),
    parent 									CHARACTER VARYING(255) ,
    volume_type 							CHARACTER VARYING(255), 
    virtual_app_id 							CHARACTER VARYING(255),
    iops 									CHARACTER VARYING(255), 
    termination_protection 					CHARACTER VARYING(255) ,
    delete_on_termination 					BOOLEAN,
    task_id 								CHARACTER VARYING(255),
    profile_id                              CHARACTER VARYING(255),
    provider_type                           CHARACTER VARYING(255),
    owner_id 								CHARACTER VARYING(255) ,
    owner_pk 								CHARACTER VARYING(255),
    tenant_id 								CHARACTER VARYING(255),
    tenant_pk 								CHARACTER VARYING(255),
    created_by 								CHARACTER VARYING(255) ,
    created_date 							TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by 						CHARACTER VARYING(255), 
    last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
    deleted 								BOOLEAN,
    inactive 								BOOLEAN,
    lock_version 							INTEGER,
    uuid 									CHARACTER VARYING(255) ,
    CONSTRAINT pca_discoveryvolume_pkey 	PRIMARY KEY (id),
    CONSTRAINT discovery_volume_volume_id_name_key UNIQUE (volume_id, name,task_id)
);

CREATE TABLE provider_profile(
 id 										CHARACTER VARYING(255) NOT NULL,
 profile_name 								CHARACTER VARYING(255) NOT NULL,
 provider_type 								CHARACTER VARYING(255) NOT NULL,
 provider_classification                    CHARACTER VARYING(255) NOT NULL,
 imported                                   BOOLEAN NOT NULL DEFAULT FALSE,
 migration_status                           CHARACTER VARYING(255),
 migration_run_on						    TIMESTAMP WITHOUT TIME ZONE,
 created_by 								CHARACTER VARYING(255),
 created_date 								TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							CHARACTER VARYING(255),
 last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
 deleted                                    BOOLEAN,
 inactive                                   BOOLEAN,
 lock_version                               INTEGER,
 owner_pk                                   CHARACTER VARYING(255),
 uuid                                       CHARACTER VARYING(255),
 tenant_pk                                  CHARACTER VARYING(255),
 owner_id                                   CHARACTER VARYING(255),
 tenant_id                          		CHARACTER VARYING(255),
 last_run_on 						        TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_provider_profile_pkey  	    PRIMARY KEY (id)
  
);

CREATE TABLE provider_profile_detail(
 id 										   CHARACTER VARYING(255) NOT NULL,
 profile_id 								   CHARACTER VARYING(255) NOT NULL,
 key 								           CHARACTER VARYING(255) NOT NULL,
 value                                         CHARACTER VARYING(255) NOT NULL,
 created_by 								   CHARACTER VARYING(255),
 created_date 								   TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							   CHARACTER VARYING(255),
 last_modified_date 						   TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_provider_profile_detail_pkey   PRIMARY KEY (id),
 CONSTRAINT provider_profile_detail_profileid_to_profileid FOREIGN  KEY (profile_id) REFERENCES provider_profile(id)
  
);
CREATE TABLE discovery_perf(
 id 										CHARACTER VARYING(255) NOT NULL,
 profile_id                                 CHARACTER VARYING(255) NOT NULL,
 reference_id                               CHARACTER VARYING(255),
 entity_type                                CHARACTER VARYING(255) NOT NULL,
 entity_id                                  CHARACTER VARYING(255) NOT NULL,
 info_data                                  text,
 vm_id                                      CHARACTER VARYING(255) NOT NULL,
 memory_used                                INTEGER,
 memory_installed                           INTEGER,           
 memory_use_in_percentage                   NUMERIC(5,2),
 cpu_used                                   INTEGER,
 cpu_installed                              INTEGER,
 cpu_current_guest                          CHARACTER VARYING(255),
 cpu_hypervisor                             CHARACTER VARYING(255),
 cpu_usage_in_percentage                    NUMERIC(5,2),
 disk_used                                  INTEGER,
 disk_installed                             INTEGER,   
 disk_read                                  BIGINT,
 disk_write                                 BIGINT,
 disk_read_latency                          BIGINT,
 disk_write_latency                         BIGINT,
 disk_iops_utilization                      BIGINT,
 deleted                            		BOOLEAN,
 inactive                           		BOOLEAN,
 lock_version                       		INTEGER,
 uuid                               		CHARACTER VARYING(255),
 owner_pk                           		CHARACTER VARYING(255),
 owner_id                           		CHARACTER VARYING(255),
 created_by 								CHARACTER VARYING(255),
 created_date 								TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							CHARACTER VARYING(255),
 last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_discovery_perf_pkey  	    PRIMARY KEY (id)
 
);

CREATE TABLE discovery_perf_data(
 id 										CHARACTER VARYING(255) NOT NULL,
 profile_id                                 CHARACTER VARYING(255) NOT NULL,
 vm_id                                      CHARACTER VARYING(255) NOT NULL,
 counter_name                               CHARACTER VARYING(255) NOT NULL,
 type                           			CHARACTER VARYING(255) NOT NULL,
 timestamp                                	TIMESTAMP WITHOUT TIME ZONE,
 value						                NUMERIC(5,2),
 deleted                            		BOOLEAN,
 inactive                           		BOOLEAN,
 lock_version                       		INTEGER,
 uuid                               		CHARACTER VARYING(255),
 owner_pk                           		CHARACTER VARYING(255),
 owner_id                           		CHARACTER VARYING(255),
 created_by 								CHARACTER VARYING(255),
 created_date 								TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							CHARACTER VARYING(255),
 last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_discovery_perf_data_pkey  	    PRIMARY KEY (id)
);

CREATE TABLE discovery_profile_analysis(
 id 										CHARACTER VARYING(255) NOT NULL,
 profile_id                                 CHARACTER VARYING(255),
 jsondata							        text,
 created_by 								CHARACTER VARYING(255),
 created_date 								TIMESTAMP WITHOUT TIME ZONE,
 last_modified_by 							CHARACTER VARYING(255),
 last_modified_date 						TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT pca_discovery_profile_analysis_pkey  	PRIMARY KEY (id)
  
); 

ALTER TABLE docker_server add column vm_id CHARACTER VARYING(255);
ALTER TABLE docker_server ADD COLUMN sourcetype CHARACTER VARYING(255);

