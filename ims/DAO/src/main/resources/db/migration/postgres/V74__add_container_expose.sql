-- Table: container_expose
-- author Intesar Mohammed

CREATE TABLE container_expose (
    container_id character varying(255) NOT NULL,
    expose character varying(255)
);

ALTER TABLE ONLY container_expose
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfvcef FOREIGN KEY (container_id) REFERENCES container(id);

