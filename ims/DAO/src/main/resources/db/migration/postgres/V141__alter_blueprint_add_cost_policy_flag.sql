-- Table: BLUEPRINT

-- Adding a new Column cost policy flag  -  Valid values: TRUE or empty

ALTER TABLE BLUEPRINT ADD COLUMN COST_POLICY_EXIST character varying(100);

