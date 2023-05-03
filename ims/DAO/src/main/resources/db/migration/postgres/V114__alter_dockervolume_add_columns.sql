-- Table: dockervolume

-- Adding a new Columns 

ALTER TABLE DOCKER_VOLUME ADD COLUMN HOST_IP character varying(255);
ALTER TABLE DOCKER_VOLUME ADD COLUMN CLUSTER_NAME character varying(255);
