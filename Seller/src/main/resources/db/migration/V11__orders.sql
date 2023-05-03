CREATE SEQUENCE vendors_id_seq START 1;
CREATE SEQUENCE orders_id_seq START 1;


CREATE TABLE public.vendors
(
  id bigint NOT NULL DEFAULT nextval('vendors_id_seq'::regclass),
  address character varying(255),
  contact_person character varying(255),
  name character varying(255),
  url character varying(255),
  CONSTRAINT vendors_pkey PRIMARY KEY (id)
);






CREATE TABLE public.orders
(
  id bigint NOT NULL DEFAULT nextval('orders_id_seq'::regclass),
  name character varying(255),
  notes character varying(255),
  order_date character varying(255),
  order_reference_number character varying(255),
  status_id character varying(255),
  vendors_id bigint,
  CONSTRAINT orders_pkey PRIMARY KEY (id),
  CONSTRAINT fkch5jiw73w6t6h3qj6288t91fw FOREIGN KEY (vendors_id)
      REFERENCES public.vendors (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_j9qiwtxjvku8r3kv76t3nve5c UNIQUE (vendors_id)
);