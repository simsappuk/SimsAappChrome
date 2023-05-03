-- Author Intesar Mohammed
-- Description: User.email solr column name changed to *_s this allows CA/TA to query users by full/partial email.

insert into code_exec_version ( version, description, bean, method, params, status, interface_fqn)
values ('232','Reindex User entity','usersServiceImpl','reindex','','NEW','com.dchq.service.security.UsersService')
