-- Table: data_center
-- Add approval_enforced columns

ALTER TABLE data_center ADD COLUMN approval_enforced boolean;
UPDATE data_center set approval_enforced = false where approval_enforced is NULL;
