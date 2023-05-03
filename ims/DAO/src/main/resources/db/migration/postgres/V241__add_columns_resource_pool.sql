
-- Author: Mohammed Luqman Shareef

alter table resource_pool add column cpu_reserved integer;
alter table resource_pool add column mem_reserved integer;
alter table resource_pool add column disk_reserved integer;

update resource_pool set cpu_reserved = cpu_used;
update resource_pool set mem_reserved = mem_used;
update resource_pool set disk_reserved = disk_used;
