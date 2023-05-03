-- Author: Mohammed Luqman Shareef

alter table data_center add column default_volume_provider_id character varying(255);
alter table data_center add column default_logging_provider_id character varying(255);
alter table data_center add column default_cloud_provider_id character varying(255);
  
  