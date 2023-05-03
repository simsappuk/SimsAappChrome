ALTER TABLE reset ADD COLUMN next_reset_quantity integer;
ALTER TABLE reset ADD COLUMN next_reset_price CHARACTER VARYING(255);
ALTER TABLE reset ADD COLUMN dual_mode boolean;