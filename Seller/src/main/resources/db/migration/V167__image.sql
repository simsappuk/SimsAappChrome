CREATE TABLE public.image
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default",
    type character varying COLLATE pg_catalog."default",
    pic_byte bytea,
    item_id character varying COLLATE pg_catalog."default",
    image_item_id character varying COLLATE pg_catalog."default",
    vinted character varying COLLATE pg_catalog."default",
    CONSTRAINT image_pkey PRIMARY KEY (id),
    CONSTRAINT images_added_fkey FOREIGN KEY (vinted)
        REFERENCES public.vinted (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);