-- author Intesar Mohammed
-- Creates a unique constraint on VLAN_ID & DRIVER ON VIRTUAL_NETWORK_POOL TABLE.


ALTER TABLE virtual_network_pool
  ADD CONSTRAINT uk_squls3wkc0klvub8tvcbue1lb_vnp UNIQUE (driver, vlan_id);