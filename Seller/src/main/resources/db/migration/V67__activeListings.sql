CREATE TABLE public.active_Listings(
  id character varying(255),
  item_id character varying(255),
  title character varying(255),
  sku character varying(255),
  quantity_available bigint,
  start_time timestamp,
  start_price_value character varying(255),
  condition_display_name character varying(255),
  ean character varying(255),
  start_price_currency_id character varying(255),
  primary_category_name character varying(255),
  image_url character varying(255),
  owner_id character varying(255),
  round_off_price bigint,
  account_id character varying(255),
  returns character varying(255)
);