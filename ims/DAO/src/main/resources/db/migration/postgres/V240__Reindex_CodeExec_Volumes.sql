-- Author Mohammed Shoukath Ali
-- Description: volume column name updated in solr.

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn)
values ('240','Reindex User entity','dockerVolumeServiceImpl','reindex','','NEW','com.dchq.service.provider.DockerVolumeService')
