CREATE TABLE public.reset
(
  id character varying(255),
  ebay_price character varying(255),
  quantity bigint,
  existed_quantity bigint,
  ebay_quantity bigint,
  last_effective_date timestamp without time zone,
  ebay_item_id character varying(255),
  account_id character varying(255),
  owner_id character varying(255),
  updated character varying(255),
  after_price character varying(255),
  listing_type character varying(255),
  first_variation_name character varying(255),
  first_variation_value character varying(255),
  second_variation_name character varying(255),
  second_variation_value character varying(255),
  variant_sku character varying(255),
  CONSTRAINT reset_pkey PRIMARY KEY (id)
);
