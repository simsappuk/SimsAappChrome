ALTER TABLE stock DROP COLUMN item_id;
ALTER TABLE stock DROP COLUMN ean;
CREATE TABLE public.stock_item_id
(
  stock_id character varying(255) NOT NULL,
  item_id character varying(255),
  CONSTRAINT fkhqj06p3g8mo1tx6sxk2ta8sku FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.stock_ean
(
  stock_id character varying(255) NOT NULL,
  ean character varying(255),
  CONSTRAINT fk8s04mnxtfnc1awmg8r3pam23v FOREIGN KEY (stock_id)
      REFERENCES public.stock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);