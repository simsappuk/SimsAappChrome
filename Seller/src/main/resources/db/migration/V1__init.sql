CREATE SEQUENCE cart_id_seq START 1;
CREATE SEQUENCE stock_id_seq START 1;

CREATE TABLE public.cart
(
  id bigint NOT NULL DEFAULT nextval('cart_id_seq'::regclass),
  created_by character varying(255),
  created_date timestamp without time zone NOT NULL,
  modified_by character varying(255),
  modified_date timestamp without time zone,
  checkout_status character varying(255),
  custom_label character varying(255),
  detail_href character varying(255),
  email_sent character varying(255),
  feedback_receivde_by_buyer character varying(255),
  image_url character varying(255),
  item_id character varying(255),
  name character varying(255),
  order_id character varying(255),
  paid_date timestamp without time zone,
  parent_record_number character varying(255),
  position1 character varying(255),
  purchased_quantity character varying(255),
  record character varying(255),
  records character varying(255),
  sale_date timestamp without time zone,
  sale_price double precision,
  sale_currency_format character varying(255),
  seller_paid_status character varying(255),
  shipped_date timestamp without time zone,
  shipped_status character varying(255),
  sold_on character varying(255),
  title character varying(255),
  total_price double precision,
  total_currency_format character varying(255),
  tran_id character varying(255),
  url_stack character varying(255),
  CONSTRAINT cart_pkey PRIMARY KEY (id)
);

-- Table: public.stock

-- DROP TABLE public.stock;

CREATE TABLE public.stock
(
  id bigint NOT NULL DEFAULT nextval('stock_id_seq'::regclass),
  created_by character varying(255),
  created_date timestamp without time zone NOT NULL,
  modified_by character varying(255),
  modified_date timestamp without time zone,
  description character varying(255),
  name character varying(255),
  quantity integer NOT NULL,
  CONSTRAINT stock_pkey PRIMARY KEY (id)
);
