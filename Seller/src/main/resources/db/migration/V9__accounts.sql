CREATE TABLE public.accounts
(
accounts_id character varying(255) NOT NULL,
account_name character varying(255),
account_type character varying(255),
api_token character varying(255),
url character varying(255),
owner_id_pk character varying(255),
tenant_id_pk character varying(255),
 created_by character varying(255),
 modified_by character varying(255),
 created_date timestamp without time zone NOT NULL,
 modified_date timestamp without time zone,
CONSTRAINT accounts_pkey PRIMARY KEY (accounts_id)
);

