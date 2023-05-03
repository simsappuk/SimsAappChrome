CREATE TABLE public.folders_entitle_owner
(
  folders_id character varying(255) NOT NULL,
  entitle_owner character varying(255),
  CONSTRAINT fkigxk6c41kro5fxgrupykqeerh FOREIGN KEY (folders_id)
      REFERENCES public.folders (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);