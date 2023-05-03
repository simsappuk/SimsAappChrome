update plugin set script_lang = 'SHELL' where script_lang is null;

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn) 
values ('64','Reindex Plugins','pluginServiceImpl','reindex','','NEW','com.dchq.service.plugin.PluginService');

