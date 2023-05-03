-- Table: users_access_key

-- Adding a new column purpose

ALTER TABLE users_access_key ADD COLUMN purpose character varying(255);
UPDATE users_access_key SET purpose = 'API' WHERE purpose is null;

insert into app_config (id, key_, value_, description, field_type) values ('dchq.master', 'dchq.master', 'https://www.dchq.io', 'Grid Master Config' , 'readonly');
insert into app_config (id, key_, value_, description) values ('dchq.node', 'dchq.node', 'true', 'Grid Node Config');