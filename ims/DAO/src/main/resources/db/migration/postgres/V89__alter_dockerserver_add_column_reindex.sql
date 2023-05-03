-- Table: docker_server
-- author Mohammed Shoukath Ali
-- ADD Column and reindex


ALTER TABLE docker_server ADD COLUMN operating_system character varying(255);

update docker_server set operating_system = 'LINUX' where operating_system is null;

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn) 
values ('89','Docker Server Reindex','dockerServerServiceImpl','reindex','','NEW','com.dchq.service.provider.DockerServerService')

