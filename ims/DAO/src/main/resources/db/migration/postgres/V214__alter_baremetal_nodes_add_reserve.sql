-- Author: Intesar Mohammed

-- Table: baremetal_nodes

ALTER TABLE baremetal_nodes add COLUMN reserved character varying(255);
ALTER TABLE baremetal_nodes add COLUMN used_by_user  character varying(255);
