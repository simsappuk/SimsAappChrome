-- Trigger: rp_stamp on public.resource_pool

-- DROP TRIGGER rp_stamp ON public.resource_pool;

CREATE TRIGGER rp_stamp
  BEFORE UPDATE
  ON public.resource_pool
  FOR EACH ROW
  EXECUTE PROCEDURE public.resourcepool_audit();