ALTER TABLE stock ADD COLUMN buy_it_now_price_v1 double precision;
ALTER TABLE stock ADD COLUMN avg_price double precision;
ALTER TABLE stock ADD COLUMN order_ref_id character varying(255);
update stock set buy_it_now_price_v1= cast(buy_it_now_price as double precision);
ALTER TABLE stock drop COLUMN buy_it_now_price;
ALTER TABLE stock RENAME COLUMN buy_it_now_price_v1 TO buy_it_now_price;

