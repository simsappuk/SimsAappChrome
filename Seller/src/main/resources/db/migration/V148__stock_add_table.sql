CREATE TABLE public.stock_prices
(
  stock_id character varying(255) NOT NULL,
  prices integer,
  CONSTRAINT fk8sa614vxn17q5sjmsnp7smsuf FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE public.stock_quantities
(
  stock_id character varying(255) NOT NULL,
  quantities double precision,
  CONSTRAINT fkk9dk1sanl2hpjucov75o64sww FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
