CREATE SEQUENCE condition_id_seq START 1;
CREATE TABLE public.condition
(
  id bigint NOT NULL DEFAULT nextval('condition_id_seq'::regclass),
  name character varying(255),
  CONSTRAINT condition_pkey PRIMARY KEY (id)
);