CREATE TABLE public.orders_total_order_specifics
(
  orders_id character varying(255) NOT NULL,
  total_order_specifics bytea,
  CONSTRAINT fk1rubhah86veg5nigl6tsf2tbh FOREIGN KEY (orders_id)
      REFERENCES public.orders (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);