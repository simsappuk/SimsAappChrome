CREATE TABLE public.replacements
(
  id character varying(255),
  title CHARACTER VARYING(255),
  record_number bigint,
  order_id CHARACTER VARYING(255),
  buyer_name CHARACTER VARYING(255),
  buyer_street1 CHARACTER VARYING(255),
  buyer_street2 CHARACTER VARYING(255),
  buyer_city CHARACTER VARYING(255),
  buyer_postal_code CHARACTER VARYING(255),
  buyer_state CHARACTER VARYING(255),
  buyer_country CHARACTER VARYING(255),
  quantity bigint,
  updated_on timestamp without time zone,
  CONSTRAINT replacements_pkey PRIMARY KEY (id)
);
