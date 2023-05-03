ALTER TABLE orders ADD COLUMN pdf_file bytea;
ALTER TABLE orders ADD COLUMN payment_method CHARACTER VARYING(255);