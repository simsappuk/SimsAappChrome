DROP TABLE relist;
CREATE SEQUENCE relist_id_seq START 1;
CREATE TABLE public.relist(
id character varying(255) NOT NULL DEFAULT nextval('relist_id_seq'::regclass),
link character varying(255),
item_id character varying(255),
new_item_id character varying(255),
account_id character varying(255),
total_quantity bigint,
remaining_quantity bigint,
re_list_quantity bigint,
owner_id character varying(255),
last_effective_date timestamp,
listed_quantity bigint,
buy_it_now_price CHARACTER VARYING(255),
title CHARACTER VARYING(255),
sku CHARACTER VARYING(255),
price CHARACTER VARYING(255),
currency CHARACTER VARYING(255),
CONSTRAINT relist_pkey PRIMARY KEY (id)
);
CREATE TABLE public.relist_item_id_list
(
  relist_id character varying(255) NOT NULL,
  item_id_list character varying(255),
  CONSTRAINT fkpgjgnnirb1ts2ri9kyve988hr FOREIGN KEY (relist_id)
      REFERENCES public.relist (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
