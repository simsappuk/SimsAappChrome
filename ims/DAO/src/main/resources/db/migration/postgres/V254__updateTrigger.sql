-- Function: public.resourcepool_audit()

-- DROP FUNCTION public.resourcepool_audit();

CREATE OR REPLACE FUNCTION public.resourcepool_audit()
  RETURNS trigger AS
$BODY$
DECLARE
   _rec record;
   _rec_sibling public.resource_pool%rowtype;
tstmp timestamp;
    BEGIN
    select * into _rec from public.resource_pool where id=NEW.id;
    --  Using   SCD 3  on resource_pool
    IF OLD.rp_type='QUOTA' OR OLD.rp_type='RESOURCE_POOL' then
        IF _rec.cpu_used!=NEW.cpu_used or  NEW.mem_used!=_rec.mem_used or NEW.disk_used!=_rec.disk_used  THEN
        tstmp:=CURRENT_TIMESTAMP;
	update public.resource_pool_audit set audit_date_end=tstmp  where id=NEW.id and audit_date_end is null;
	--RAISE EXCEPTION MESSAGE ;
-- Adding entry in audit  table
   insert into  public.resource_pool_audit  select id,created_by,created_date,last_modified_by,last_modified_date,deleted,inactive,lock_version,owner_pk,uuid,tenant_pk,entitlement,entitlement_type,name,owner_id,tenant_id,cpu,mem,disk,provider_id,cpu_used,mem_used,disk_used,rp_type,az_id,az_name,cpu_reserved,
mem_reserved,disk_reserved,tstmp
from resource_pool where id=old.id;

insert into  public.resource_pool_daily  select id,created_by,created_date,last_modified_by,last_modified_date,deleted,inactive,lock_version,owner_pk,uuid,tenant_pk,entitlement,entitlement_type,name,owner_id,tenant_id,cpu,mem,disk,provider_id,cpu_used,mem_used,disk_used,rp_type,az_id,az_name,cpu_reserved,
mem_reserved,disk_reserved,tstmp from resource_pool  where inactive='f' and rp_type='RESOURCE_POOL';

RAISE INFO '% Moved to audit',old.rp_type;

        END IF;
        END IF;
       -- RAISE EXCEPTION 'Error in Audit  % ', _rec.id ;

        RETURN NEW;
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.resourcepool_audit()
  OWNER TO dchq;
