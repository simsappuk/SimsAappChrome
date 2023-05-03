CREATE TABLE public.stock_item_details
(
  stock_id character varying(255) NOT NULL,
  item_id character varying(255),
  price double precision,
  quantity integer,
  CONSTRAINT fkgjglsjj6af1a6nb3wfvb9f4tl FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);