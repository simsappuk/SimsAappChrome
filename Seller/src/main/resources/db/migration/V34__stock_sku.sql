CREATE TABLE public.stock_sku
(
  stock_id CHARACTER VARYING,
  sku character varying(255),
  CONSTRAINT fksn9pgn4qwtotidjj2b826c2so FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);