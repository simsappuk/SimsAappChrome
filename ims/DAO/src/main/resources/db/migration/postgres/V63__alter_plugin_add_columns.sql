-- Table: plugin 

-- Adding a  timeout, script lang and scriptargs
-- 

ALTER TABLE plugin ADD COLUMN timeout integer;
ALTER TABLE plugin ADD COLUMN script_lang character varying(255);
ALTER TABLE plugin ADD COLUMN script_args character varying(255);
