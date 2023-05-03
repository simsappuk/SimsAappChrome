CREATE TABLE public.external_stock_links(
id character varying(255),
link character varying(255),
cex_id character varying(255),
account_id character varying(255),
owner_id character varying(255),
condition character varying(255),
sku character varying(255),
price character varying(255),
title character varying(255),
availability character varying(255),
out_of_stock BOOLEAN,
quantity bigint
);