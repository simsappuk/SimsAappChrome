--  Update all existing Data Center's blue print entitlement

update data_center set blueprint_entitlement_type = 'ALL_BLUEPRINTS' where blueprint_entitlement_type is null;
