CREATE TABLE public.vinted_listing
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    stock_id character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT vinted_listing_pkey PRIMARY KEY (id)
)