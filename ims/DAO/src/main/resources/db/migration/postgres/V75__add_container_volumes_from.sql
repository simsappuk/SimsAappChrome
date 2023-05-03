-- Table: container_volumes_from
-- author Intesar Mohammed

CREATE TABLE container_volumes_from (
    container_id character varying(255) NOT NULL,
    volumes_from character varying(255)
);

ALTER TABLE ONLY container_volumes_from
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfvcvf FOREIGN KEY (container_id) REFERENCES container(id);

