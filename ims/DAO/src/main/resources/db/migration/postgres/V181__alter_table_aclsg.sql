-- Author: Mohammed Shoukath Ali
-- Table: network_acl, security_group


DROP TABLE IF EXISTS network_acl_rules;


CREATE TABLE network_acl_rules
(
  network_acl_id character varying(255) NOT NULL,
  rules_id character varying(255) NOT NULL,
  CONSTRAINT fk_grdeb6f3vnhny72cmq0gmbx1u FOREIGN KEY (rules_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ovlj7b04ayr6su5sxlflm3m0f FOREIGN KEY (network_acl_id)
      REFERENCES network_acl (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_grdeb6f3vnhny72cmq0gmbx1u UNIQUE (rules_id)
);

DROP TABLE IF EXISTS security_group_rules;



CREATE TABLE security_group_rules
(
  security_group_id character varying(255) NOT NULL,
  rules_id character varying(255) NOT NULL,
  CONSTRAINT fk_6qtn2qnheql3gucusgt9qb8y9 FOREIGN KEY (rules_id)
      REFERENCES rule (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_j47n7qdogdyxkhp86twuwgxqo FOREIGN KEY (security_group_id)
      REFERENCES security_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_6qtn2qnheql3gucusgt9qb8y9 UNIQUE (rules_id)
);










