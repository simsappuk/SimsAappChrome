
alter table app_config add column field_type character varying(25);

insert into app_config (id, key_, value_, description) values ('self.register.user', 'self.register.user', 'No', 'Enables User Sign up from Home page');
insert into app_config (id, key_, value_, description) values ('self.register.tenant', 'self.register.tenant', 'No', 'Enables Tenant Sign up from Home page');

insert into app_config (id, key_, value_, description,field_type) values ('hyperForm.edition', 'hyperForm.edition', 'Enterprise Edition', '','readonly');
insert into app_config (id, key_, value_, description,field_type) values ('hyperForm.version', 'hyperForm.version', '5.0.0.0', '','readonly');
