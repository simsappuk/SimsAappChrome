--alter table resource_pool_audit drop column description;
--  alter table resource_pool_daily drop column description;
alter table resource_pool_audit add column description  character varying(255);
alter table resource_pool_daily add column description  character varying(255);


-- Trigger: rp_stamp on public.resource_pool

 DROP TRIGGER rp_stamp ON public.resource_pool;

CREATE TRIGGER rp_stamp
  AFTER UPDATE
  ON public.resource_pool
  FOR EACH ROW
  EXECUTE PROCEDURE public.resourcepool_audit();



-- Function: public.resourcepool_audit()

-- DROP FUNCTION public.resourcepool_audit();

CREATE OR REPLACE FUNCTION public.resourcepool_audit()
  RETURNS trigger AS
$BODY$
DECLARE
   _rec record;
   _rec_sibling public.resource_pool%rowtype;
   _count int;
   v_description varchar;
tstmp timestamp;

    BEGIN

    --  Using   SCD 3  on resource_pool
v_description:='';
IF  NEW.disk_used<0 then NEW.disk_used:=0;end if;
IF  NEW.cpu_used<0 then NEW.cpu_used:=0;end if;
IF  NEW.mem_used<0 then NEW.mem_used:=0;end if;
RAISE INFO 'New Disk % , Old Disk %',NEW.disk_used,OLD.disk_used;
if (OLD.cpu_used-NEW.cpu_used)>0 then v_description:=OLD.cpu_used-NEW.cpu_used||' Cores Removed, ' ;
ELSIF (OLD.cpu_used-NEW.cpu_used)<0  then v_description:=-OLD.cpu_used+NEW.cpu_used||'Cores Added, ' ;
  END IF;
if (OLD.mem_used-NEW.mem_used)>0 then v_description:=v_description||(OLD.mem_used-NEW.mem_used)||' GB Memory Removed, ' ;
ELSIF (OLD.mem_used-NEW.mem_used)<0  then v_description:=v_description||(-OLD.mem_used+NEW.mem_used)||'GB Memory Added, ' ;
  END IF;
    if (OLD.disk_used-NEW.disk_used)>0 then v_description:=v_description||(OLD.disk_used-NEW.disk_used)||' GB Disk Removed' ;
ELSIF (OLD.disk_used-NEW.disk_used)<0  then v_description:=v_description||(-OLD.disk_used+NEW.disk_used)||'GB Disk Added' ;
  END IF;

  -- NEW.description:=v_description;
    RAISE INFO 'Disk % ',v_description;
    IF OLD.rp_type='QUOTA' OR OLD.rp_type='RESOURCE_POOL' then
        IF OLD.cpu_used!=NEW.cpu_used or  NEW.mem_used!=OLD.mem_used or NEW.disk_used!=OLD.disk_used  THEN
        tstmp:=CURRENT_TIMESTAMP;
        RAISE INFO 'INSIDE New Disk % , Old Disk %',NEW.disk_used,OLD.disk_used;
	update public.resource_pool_audit set audit_date_end=tstmp  where id=NEW.id and audit_date_end is null;
	--RAISE EXCEPTION MESSAGE ;
-- Adding entry in audit  table
   insert into  public.resource_pool_audit (id,created_by,created_date,last_modified_by,last_modified_date,deleted,inactive,lock_version,owner_pk,uuid,tenant_pk,entitlement,entitlement_type,name,owner_id,tenant_id,cpu,mem,disk,provider_id,cpu_used,mem_used,disk_used,rp_type,az_id,az_name,cpu_reserved,
mem_reserved,disk_reserved,audit_date,description) select id,created_by,created_date,last_modified_by,last_modified_date,deleted,inactive,lock_version,owner_pk,uuid,tenant_pk,entitlement,entitlement_type,name,owner_id,tenant_id,cpu,mem,disk,provider_id,cpu_used,mem_used,disk_used,rp_type,az_id,az_name,cpu_reserved,
mem_reserved,disk_reserved,tstmp,v_description
from resource_pool where id=old.id;



select count(*) into _count from resource_pool  where inactive='f' and rp_type='RESOURCE_POOL';

insert into resource_pool_daily
select id||'-'||random() as id ,created_by,created_date,last_modified_by,last_modified_date,deleted,inactive,lock_version,owner_pk,uuid,tenant_pk,entitlement,entitlement_type,name,owner_id,tenant_id,cpu,mem,disk,provider_id,cpu_used,mem_used,disk_used,rp_type,az_id,az_name,cpu_reserved,
mem_reserved,disk_reserved,tstmp,id,id,case when id=OLD.id then v_description  else '' end from resource_pool where inactive='f' and (rp_type='RESOURCE_POOL' or  rp_type='QUOTA');

RAISE INFO '% Moved to audit rows (%)',old.rp_type,_count;

        END IF;
        END IF;
       -- RAISE EXCEPTION 'Error in Audit  % ', OLD.id ;

        RETURN NEW;
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.resourcepool_audit()
  OWNER TO dchq;
