ALTER TABLE orders ADD COLUMN modified_date timestamp without time zone;
ALTER TABLE orders ADD COLUMN modified_by character varying(255);
