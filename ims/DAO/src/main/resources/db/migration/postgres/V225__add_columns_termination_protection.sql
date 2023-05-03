
-- Author: Mohammed Luqman Shareef


alter table docker_server add column termination_protection character varying(255);
alter table provision add column termination_protection character varying(255);
alter table docker_volume add column termination_protection character varying(255);
alter table virtual_app add column termination_protection character varying(255);