CREATE TABLE public.pdf
(
  id character varying(255) NOT NULL,
  buyer_country character varying(255),
  buyer_name character varying(255),
  buyer_street1 character varying(255),
  buyer_street2 character varying(255),
  buyer_user_id character varying(255),
  item_discount character varying(255),
  paid_date character varying(255),
  payment_method character varying(255),
  placed_date character varying(255),
  postage_fee character varying(255),
  sub_total character varying(255),
  total_amount character varying(255),
  CONSTRAINT pdf_pkey PRIMARY KEY (id)
);
CREATE TABLE public.pdf_order_details
(
  pdf_id character varying(255) NOT NULL,
  delivery_service character varying(255),
  extended_order_id character varying(255),
  item_price character varying(255),
  quantity character varying(255),
  seller_user_id character varying(255),
  title character varying(255),
  CONSTRAINT fk6o08lbclwntjly5vue1gxxr3a FOREIGN KEY (pdf_id)
      REFERENCES public.pdf (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);