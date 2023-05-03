DROP TABLE compete;
CREATE TABLE public.compete
(
  id character varying(255),
  link character varying(255),
  seller_price character varying(255),
  ebay_price character varying(255),
  quantity bigint,
  check_revise character varying(255),
  subtract_value character varying(255),
  last_effective_date timestamp without time zone,
  ebay_item_id character varying(255),
  account_id character varying(255),
  owner_id character varying(255),
  listing_type character varying(255),
  first_variation_name character varying(255),
  first_variation_value character varying(255),
  second_variation_name character varying(255),
  second_variation_value character varying(255),
  variant_sku character varying(255),
    CONSTRAINT compete_pkey PRIMARY KEY (id)
);
CREATE TABLE public.compete_compete_item_id
(
  compete_id character varying(255) NOT NULL,
  compete_item_id character varying(255),
  CONSTRAINT fkafqdr5dur8tvt9hech03ls5er FOREIGN KEY (compete_id)
      REFERENCES public.compete (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
