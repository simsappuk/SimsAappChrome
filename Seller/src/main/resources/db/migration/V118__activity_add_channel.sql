INSERT INTO channel(id,log_name)VALUES('24','RELIST ITEMS INFO');
ALTER TABLE activity_log ADD COLUMN sku CHARACTER VARYING(255);
ALTER TABLE activity_log ADD COLUMN re_list_quantity CHARACTER VARYING(255);
CREATE TABLE public.activity_log_item_id_list
(
  activity_log_id character varying(255) NOT NULL,
  item_id_list character varying(255),
  CONSTRAINT fkh74kmjid9gn4q14m3frc13imb FOREIGN KEY (activity_log_id)
      REFERENCES public.activity_log (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
