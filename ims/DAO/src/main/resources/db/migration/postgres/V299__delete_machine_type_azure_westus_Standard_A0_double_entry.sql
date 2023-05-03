-- Author : fmohammad@hypergrid.com

-- Deleting Azure Standard_A0 machine type for West US region

DELETE FROM machine_type WHERE id = '675' and EXISTS (SELECT 1 FROM machine_type WHERE id = '675') ;