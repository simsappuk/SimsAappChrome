ALTER TABLE orders ADD COLUMN order_specifics bytea;
ALTER TABLE orders ADD COLUMN picking_type CHARACTER VARYING(255);