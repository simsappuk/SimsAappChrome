
-- Author: sabedeen@hypergrid.com

ALTER TABLE public.machine_type ADD COLUMN machine_group character varying(255);
delete from  machine_type;