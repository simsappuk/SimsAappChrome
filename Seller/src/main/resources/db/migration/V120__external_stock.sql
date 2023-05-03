ALTER TABLE external_stock_links ADD COLUMN last_effective_date timestamp;
ALTER TABLE accounts ADD COLUMN update_indicator BOOLEAN;