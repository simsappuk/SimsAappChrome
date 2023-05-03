-- add new column free_form_entitlement
-- update existing records free_form_entitlement column value to true

ALTER TABLE registry_account DROP COLUMN free_form_entitlement;
ALTER TABLE registry_account ADD COLUMN free_form_entitlement boolean;
UPDATE registry_account set free_form_entitlement = TRUE WHERE free_form_entitlement IS NULL;