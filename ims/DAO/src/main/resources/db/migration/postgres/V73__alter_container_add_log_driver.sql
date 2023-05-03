-- Table: container
-- author Intesar Mohammed

ALTER TABLE container ADD COLUMN log_driver character varying(255);


-- Table: container_attributes

CREATE TABLE container_attributes
(
  container_id character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  val character varying(512),
  CONSTRAINT container_attributes_pkey PRIMARY KEY (container_id, name),
  CONSTRAINT fk_m1r19tcgpqa58i592f8et1cld FOREIGN KEY (container_id) REFERENCES container (id)
)



