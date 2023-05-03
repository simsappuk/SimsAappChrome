-- Index on table server_monitor_stat
-- Author: Mohammed Luqman Shareef


CREATE INDEX server_monitor_stat_index
  ON server_monitor_stat
  USING btree
  (server_id,dt );