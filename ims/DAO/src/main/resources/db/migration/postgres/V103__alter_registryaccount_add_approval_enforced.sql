-- Table: registry account
-- Add approval_enforced columns

ALTER TABLE registry_account ADD COLUMN approval_enforced boolean;
UPDATE registry_account set approval_enforced = false where approval_enforced is NULL;
