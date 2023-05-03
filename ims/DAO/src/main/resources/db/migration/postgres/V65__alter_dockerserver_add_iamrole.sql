-- Table: docker_server

-- UPDATE Column iam_profile

ALTER TABLE docker_server ADD COLUMN iam_profile character varying(255);
