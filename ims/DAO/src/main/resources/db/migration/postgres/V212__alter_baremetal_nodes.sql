    ALTER TABLE baremetal_nodes add COLUMN serial_number character varying(255);
    ALTER TABLE baremetal_nodes add COLUMN power_on Boolean;
    ALTER TABLE baremetal_nodes add COLUMN post_state  character varying(255);
    ALTER TABLE baremetal_nodes add COLUMN processor_count integer;
    ALTER TABLE baremetal_nodes add COLUMN processor_family character varying(255);
    ALTER TABLE baremetal_nodes add COLUMN model character varying(255);
    ALTER TABLE baremetal_nodes add COLUMN manufacturer character varying(255);
    ALTER TABLE baremetal_nodes add COLUMN memory_gib integer;
    ALTER TABLE baremetal_nodes add COLUMN bios_version character varying(255);