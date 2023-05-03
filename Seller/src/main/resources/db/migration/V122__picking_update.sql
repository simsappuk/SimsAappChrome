CREATE TABLE public.label_updates
(
  id character varying(255),
  initial_record_number character varying(255),
  account_id character varying(255),
  owner_id character varying(255),
  selected character varying(255),
  final_record_number character varying(255),
  last_effective_date  timestamp without time zone,
    CONSTRAINT label_updates_pkey PRIMARY KEY (id)
);