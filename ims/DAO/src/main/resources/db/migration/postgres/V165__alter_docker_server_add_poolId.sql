-- Alter table docker_server add columns resource_pool_id

ALTER TABLE docker_server
    ADD COLUMN resource_pool_id character varying(255);	    

