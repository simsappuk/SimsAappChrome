-- Columns added for Swarm service mode

alter table provision add column docker_service_id character varying(255);

alter table container add column service_mode boolean;
