-- Index on table entity_log
-- Author: Intesar Mohammed


CREATE INDEX entity_log_index
  ON entity_log
  USING btree
  (entity_type, entity_id, secondary_id );