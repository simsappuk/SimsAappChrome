
DROP TRIGGER rp_stamp ON public.resource_pool;
delete from  public.resource_pool_daily;
delete  from  public.resource_pool_audit;
DROP FUNCTION public.resourcepool_audit();
alter table public.resource_pool_daily DROP COLUMN day_id;
ALTER TABLE public.resource_pool_daily ADD COLUMN day_id date;