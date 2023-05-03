CREATE TABLE public.spreadsheet_entitle_owner
(
  spreadsheet_id character varying(255) NOT NULL,
  entitle_owner character varying(255),
  CONSTRAINT fkdggu4wugdwwffvjadsnrf65sd FOREIGN KEY (spreadsheet_id)
      REFERENCES public.spreadsheet (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);