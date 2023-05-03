CREATE TABLE public.scheduler(
  id character varying(255) ,
  name character varying(255),
  status character varying(255),
  date timestamp without time zone,
  CONSTRAINT scheduler_pkey PRIMARY KEY (id)
  );