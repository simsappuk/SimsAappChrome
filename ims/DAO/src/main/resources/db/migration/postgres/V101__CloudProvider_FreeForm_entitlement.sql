-- add new column free_form_entitlement
-- update existing records free_form_entitlement column value to true

alter table registry_account add column free_form_entitlement character varying(255);

update registry_account set free_form_entitlement = 'true' where free_form_entitlement is null;
