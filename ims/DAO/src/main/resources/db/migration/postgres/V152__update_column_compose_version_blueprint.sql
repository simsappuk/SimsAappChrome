-- Table: blueprint

-- UPDATE Column compose_version to vm

update blueprint set compose_version = 'VM' where  blueprint_type = 'VM_COMPOSE';

