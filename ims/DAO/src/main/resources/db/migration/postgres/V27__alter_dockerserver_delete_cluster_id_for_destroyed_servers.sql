-- Table: docker_server
-- Remove cluster_id for destroyed servers

UPDATE DOCKER_SERVER SET DATA_CENTER_ID=NULL WHERE DATA_CENTER_ID != '' AND DOCKER_SERVER_STATUS LIKE 'DESTROYED';
