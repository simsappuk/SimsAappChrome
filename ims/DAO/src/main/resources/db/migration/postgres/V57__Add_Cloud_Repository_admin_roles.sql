-- Apply the roles 'ROLE_CLOUD_PROVIDER_ADMIN' and 'ROLE_REPOSITORIES_ADMIN' to all the users

-- If already the role was defined, delete it to avoid duplicate
delete from users_authorities where authorities  in ( 'ROLE_CLOUD_PROVIDER_ADMIN' , 'ROLE_REPOSITORIES_ADMIN' );

insert into users_authorities ( users_id, authorities ) select id, 'ROLE_CLOUD_PROVIDER_ADMIN' from users;

insert into users_authorities ( users_id, authorities ) select id, 'ROLE_REPOSITORIES_ADMIN' from users;
