CREATE TABLE public.vinted_data
(
    account_id character varying(255) COLLATE pg_catalog."C" NOT NULL,
    action character varying(255) COLLATE pg_catalog."C" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    id character varying(255) COLLATE pg_catalog."C" NOT NULL,
    CONSTRAINT vinteddata_pkey PRIMARY KEY (id)
);