ALTER TABLE compete DROP COLUMN item_id;
ALTER TABLE compete ADD COLUMN ebay_item_id CHARACTER VARYING(255);
ALTER TABLE compete ADD COLUMN compete_item_id CHARACTER VARYING(255);