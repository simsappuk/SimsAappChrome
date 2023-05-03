-- Mohammed Luqman Shareef
-- Added new Colmns to datat_center for Security Controls

	ALTER TABLE DATA_CENTER ADD COLUMN mem_limit character varying(50);
	
	ALTER TABLE DATA_CENTER ADD COLUMN cpu_shares integer;
	
	ALTER TABLE DATA_CENTER ADD COLUMN privileged character varying(50);

	ALTER TABLE DATA_CENTER ADD COLUMN cap_add character varying(50);

	ALTER TABLE DATA_CENTER ADD COLUMN auto_sdn character varying(50);
	