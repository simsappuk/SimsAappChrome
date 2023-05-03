-- ALTER Docker_Networks
-- Author: Mohammed Luqman Shareef

alter table docker_network add column data_center_name character varying(255);

alter table docker_network add column docker_server_name character varying(255);