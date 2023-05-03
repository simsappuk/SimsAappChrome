-- Add description to Rule

ALTER TABLE public.rule
    ADD COLUMN description character varying(255);