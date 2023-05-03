-- author Mohammed Shoukath Ali
-- Rename column next_cron_date to next_cron_date_
ALTER TABLE build_plugin_profile RENAME COLUMN next_cron_date_ TO cron_date;

ALTER TABLE build_plugin_profile drop  COLUMN build_name;
  
  
