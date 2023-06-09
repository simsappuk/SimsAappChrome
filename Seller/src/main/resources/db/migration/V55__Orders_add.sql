ALTER TABLE orders ADD COLUMN order_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN buyer_email_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN selling_record_number CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN buyer_name CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN shipped_to_country CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN order_status CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN original_item_price CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN seller_email CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN buyer_user_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN seller_user_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN cancel_status CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN extended_order_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN total_amount CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN adjustment_amount CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN amount_paid CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN transaction_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN transaction_date timestamp;
ALTER TABLE orders ADD COLUMN last_modified_date timestamp;
ALTER TABLE orders ADD COLUMN item_discount_amount CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN sub_total CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN total_amount_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN sub_amount_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN condition_display_name CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN quantity_purchased CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN vat_percent CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN grand_total CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN item_discount_price CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN shipping_service_cost CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN shipping_service_cost_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN transaction_price CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN grand_total_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN original_item_price_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN discount_price_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN adjustment_amount_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN transaction_price_currency_id CHARACTER VARYING(255);
ALTER TABLE orders ADD COLUMN item_image CHARACTER VARYING(255);




