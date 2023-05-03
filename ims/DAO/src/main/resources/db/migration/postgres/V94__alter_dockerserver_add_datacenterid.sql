-- Table: docker_server

-- UPDATE Column data_center_id character

ALTER TABLE docker_server ADD COLUMN data_center_pk character varying(255);

UPDATE docker_server set data_center_pk = data_center_id;

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn)
values ('94','Reindex DockerServer entity','dockerServerServiceImpl','reindex','4028818650d4aca10150d4bf63470001v94','NEW','com.dchq.service.provider.DockerServerService')

