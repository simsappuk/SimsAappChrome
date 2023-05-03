-- Alter table capcity add columns userd_disk, used_cores, used_memeory

ALTER TABLE capacity
	    ADD COLUMN used_cores integer;
	
ALTER TABLE capacity
	    ADD COLUMN used_memory double precision;
	
ALTER TABLE capacity
	    ADD COLUMN used_disk_memory double precision;	

