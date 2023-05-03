CREATE TABLE public.relist(
id character varying(255),
link character varying(255),
item_id character varying(255),
new_item_id character varying(255),
account_id character varying(255),
total_quantity bigint,
remaining_quantity bigint,
re_list_quantity bigint,
owner_id character varying(255),
last_effective_date timestamp
);