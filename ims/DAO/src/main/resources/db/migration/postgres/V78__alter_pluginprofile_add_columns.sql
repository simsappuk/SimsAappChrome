-- Table: plugin_profile
-- author Mohammed Shoukath Ali
-- ADD Column 


ALTER TABLE plugin_profile ADD COLUMN exit_on_failure character varying(255);
ALTER TABLE plugin_profile ADD COLUMN valid_exit_code character varying(255);
ALTER TABLE plugin_profile ADD COLUMN exec_on character varying(255);
