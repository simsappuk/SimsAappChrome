-- Table: docker_volume

ALTER TABLE docker_volume ADD COLUMN volume_type character varying(255);
ALTER TABLE docker_volume ADD COLUMN iops character varying(255);


