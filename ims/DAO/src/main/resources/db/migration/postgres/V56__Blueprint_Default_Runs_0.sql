update blueprint set total_run = '0' where total_run is null;

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn) 
values ('56','Reindex blueprint entity','blueprintServiceImpl','reindex','','NEW','com.dchq.service.blueprint.BlueprintService');

