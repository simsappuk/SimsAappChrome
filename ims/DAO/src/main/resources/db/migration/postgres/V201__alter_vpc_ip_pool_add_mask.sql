-- author Intesar Mohammed
-- Virtual IP requires network mask for it to work.

ALTER TABLE vpc_ip_pool
    ADD COLUMN mask character varying(255);
