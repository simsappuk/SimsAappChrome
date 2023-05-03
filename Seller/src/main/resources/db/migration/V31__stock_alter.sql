ALTER TABLE stock ALTER COLUMN id TYPE character varying(255);
ALTER TABLE stock ALTER COLUMN last_effective_date TYPE DATE;
ALTER TABLE stock ALTER COLUMN quantity DROP NOT NULL;