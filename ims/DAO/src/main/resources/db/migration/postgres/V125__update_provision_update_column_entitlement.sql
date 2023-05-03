-- Intesar Mohammed
-- Set entitlement_type to OWNER when missing.

UPDATE provision SET entitlement_type = 'OWNER' WHERE entitlement_type IS NULL;
