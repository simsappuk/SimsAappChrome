-- Table: app_task
-- author Mohammed Shoukath Ali
-- ADD Column requeue_attempt


ALTER TABLE app_task ADD COLUMN requeue_attempt integer;
