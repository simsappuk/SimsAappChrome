ALTER TABLE public.resource_pool_daily ADD COLUMN resource_pool_id character varying(255);

--alter  table  resource_pool_daily remove  column resource_pool_id

-- Table: public.resource_pool_entitled_orgs

-- DROP TABLE public.resource_pool_entitled_orgs;

CREATE TABLE public.resource_pool_daily_entitled_orgs
(
  resource_pool_id character varying(255) NOT NULL,
  entitled_orgs_id character varying(255) NOT NULL,
  CONSTRAINT resource_pool_daily_entitled_orgs_pkey PRIMARY KEY (resource_pool_id, entitled_orgs_id),
  CONSTRAINT fk_g5lxqxairqhl2qpwk8g2qk7bk FOREIGN KEY (entitled_orgs_id)
      REFERENCES public.organization (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_gx6ovu2xjmltgbos0qw61p2sg FOREIGN KEY (resource_pool_id)
      REFERENCES public.resource_pool (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.resource_pool_daily_entitled_orgs
  OWNER TO dchq;

ALTER TABLE public.resource_pool_daily ADD COLUMN resource_pool_daily_id character varying(255);