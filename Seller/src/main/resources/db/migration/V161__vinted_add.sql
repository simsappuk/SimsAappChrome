ALTER TABLE vinted ADD COLUMN created_at character varying(255);
ALTER TABLE vinted ADD COLUMN original_price_numeric integer;
ALTER TABLE vinted ADD COLUMN item_closing_action character varying(255);