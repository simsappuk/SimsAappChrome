CREATE TABLE public.label_updates_range
(
  label_updates_id character varying(255) NOT NULL,
  range character varying(255),
  CONSTRAINT fkrdqlqhrta8cn769lu67gqwur5 FOREIGN KEY (label_updates_id)
      REFERENCES public.label_updates (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);