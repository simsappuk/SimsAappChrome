CREATE TABLE public.activity_log
(
  id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  modified_by character varying(255),
  modified_date timestamp without time zone,
  account_id character varying(255),
  action_needed character varying(255),
  buyer_id character varying(255),
  condition character varying(255),
  condition_display_name character varying(255),
  confirm character varying(255),
  date character varying(255),
  delivered_date character varying(255),
  duplicate_id character varying(255),
  item_number character varying(255),
  notes character varying(255),
  order_number character varying(255),
  order_ref character varying(255),
  owner_id character varying(255),
  price character varying(255),
  proof character varying(255),
  quantity integer,
  reason character varying(255),
  remarks character varying(255),
  return_date character varying(255),
  returned_item character varying(255),
  sales_record_number character varying(255),
  seller_id character varying(255),
  selling_price character varying(255),
  sent_date character varying(255),
  sheet_number character varying(255),
  solution character varying(255),
  spreadsheet_id character varying(255),
  title character varying(255),
  tracking_number character varying(255),
  update character varying(255),
  channel_id character varying(255),
  CONSTRAINT activity_log_pkey PRIMARY KEY (id),
  CONSTRAINT fklbb8c8y2k3mp14y4kpmhglpc1 FOREIGN KEY (channel_id)
      REFERENCES public.channel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
