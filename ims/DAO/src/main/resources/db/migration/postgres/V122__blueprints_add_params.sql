-- Create: Params
-- Author: Intesar Mohammed

CREATE TABLE blueprint_params (
    blueprint_id character varying(255) NOT NULL,
    label character varying(255),
    prop character varying(255),
    suggestion character varying(512),
    val character varying(255),
    mandatory boolean
);

ALTER TABLE ONLY blueprint_params
    ADD CONSTRAINT fk_3gpn7h0dyqc8hnqxo7m27kftg FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);