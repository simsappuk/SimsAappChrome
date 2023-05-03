ALTER TABLE resource_pool ADD COLUMN  spend_limit  character varying(255) DEFAULT 0;
ALTER TABLE resource_pool ADD COLUMN  spent  character varying(255) DEFAULT 0;