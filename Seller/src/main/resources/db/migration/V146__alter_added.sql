ALTER TABLE orders DROP COLUMN buyer_checkout_message;
ALTER TABLE orders ADD COLUMN buyer_checkout_message CHARACTER VARYING(255555);