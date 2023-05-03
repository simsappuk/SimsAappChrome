
-- Author Mohammed Luqman Shareef
-- Description: Create one Cloud Provider for HYPER_GRID and HCS_VSPHERE each and move all the existing Cloud providers as AZs under them


insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn)
values ('233','Move Cloud Providers to AZ','registryAccountServiceImpl','setupAZs','','NEW','com.dchq.service.registry.RegistryAccountService');
